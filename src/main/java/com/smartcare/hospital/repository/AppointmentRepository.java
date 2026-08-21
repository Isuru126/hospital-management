package com.smartcare.hospital.repository;

import com.smartcare.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * Finds all appointments scheduled for a specific patient.
     */
    List<Appointment> findByPatientId(Integer patientId);

    /**
     * Finds all appointments assigned to a specific doctor.
     */
    List<Appointment> findByDoctorId(Integer doctorId);

    /**
     * Checks if a doctor already has an appointment booked at a specific date and time
     * to enforce the business rule preventing scheduling clashes.
     */
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(
            Integer doctorId,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );
}