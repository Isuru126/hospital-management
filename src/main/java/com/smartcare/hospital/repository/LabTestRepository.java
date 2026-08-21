package com.smartcare.hospital.repository;

import com.smartcare.hospital.entity.LabTest;
import com.smartcare.hospital.enums.TestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {

    /**
     * Retrieves all laboratory test history for a specific patient.
     */
    List<LabTest> findByPatientId(Integer patientId);

    /**
     * Retrieves tests by their status (e.g., Pending, Completed).
     */
    List<LabTest> findByTestStatus(TestStatus testStatus);
}