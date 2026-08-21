package com.smartcare.hospital.repository;

import com.smartcare.hospital.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    /**
     * Retrieves all admission records for a patient.
     */
    List<Admission> findByPatientId(Integer patientId);

    /**
     * Finds all currently active in-patient admissions (where discharge date is null).
     */
    List<Admission> findByDischargeDateIsNull();
}