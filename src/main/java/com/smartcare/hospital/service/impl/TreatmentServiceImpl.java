package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Treatment;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.repository.TreatmentRepository;
import com.smartcare.hospital.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Treatment recordTreatment(Treatment treatment) {
        if (treatment.getTreatmentDate() == null) {
            treatment.setTreatmentDate(LocalDate.now());
        }
        return treatmentRepository.save(treatment);
    }

    @Override
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    @Override
    public Treatment getTreatmentById(Long id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id: " + id));
    }

    @Override
    public List<Treatment> getTreatmentsByPatientId(Integer patientId) {
        return treatmentRepository.findByPatientId(patientId);
    }
}