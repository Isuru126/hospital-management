package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Appointment;
import com.smartcare.hospital.enums.AppointmentStatus;
import com.smartcare.hospital.exception.AppointmentConflictException;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.repository.AppointmentRepository;
import com.smartcare.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment bookAppointment(Appointment appointment) {
        boolean hasConflict = appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                appointment.getDoctor().getId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (hasConflict) {
            throw new AppointmentConflictException("Doctor is already booked at the requested date and time.");
        }

        appointment.setStatus(AppointmentStatus.Scheduled);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment details) {
        Appointment appointment = getAppointmentById(id);
        appointment.setAppointmentDate(details.getAppointmentDate());
        appointment.setAppointmentTime(details.getAppointmentTime());
        appointment.setDoctor(details.getDoctor());
        appointment.setRoom(details.getRoom());
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment cancelAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus(AppointmentStatus.Cancelled);
        return appointmentRepository.save(appointment);
    }
}