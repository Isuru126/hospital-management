package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Admission;
import com.smartcare.hospital.entity.Room;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.exception.RoomUnavailableException;
import com.smartcare.hospital.repository.AdmissionRepository;
import com.smartcare.hospital.repository.RoomRepository;
import com.smartcare.hospital.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public AdmissionServiceImpl(AdmissionRepository admissionRepository, RoomRepository roomRepository) {
        this.admissionRepository = admissionRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Admission admitPatient(Admission admission) {
        Room room = roomRepository.findById(admission.getRoom().getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + admission.getRoom().getRoomId()));

        if (!Boolean.TRUE.equals(room.getIsAvailable())) {
            throw new RoomUnavailableException("Requested room is currently unavailable/occupied.");
        }

        room.setIsAvailable(false);
        roomRepository.save(room);

        return admissionRepository.save(admission);
    }

    @Override
    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    @Override
    public Admission getAdmissionById(Long id) {
        return admissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admission record not found with id: " + id));
    }

    @Override
    @Transactional
    public Admission dischargePatient(Long id) {
        Admission admission = getAdmissionById(id);
        admission.setDischargeDate(LocalDate.now());

        Room room = admission.getRoom();
        if (room != null) {
            room.setIsAvailable(true);
            roomRepository.save(room);
        }

        return admissionRepository.save(admission);
    }
}