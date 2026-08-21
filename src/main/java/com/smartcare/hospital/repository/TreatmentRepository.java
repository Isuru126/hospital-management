package com.smartcare.hospital.repository;

import com.smartcare.hospital.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    /**
     * Retrieves treatment and diagnosis history for a specific patient.
     */
    List<Treatment> findByPatientId(Integer patientId);

    /**
     * Retrieves all treatments prescribed by a specific doctor.
     */
    List<Treatment> findByDoctorId(Integer doctorId);
}