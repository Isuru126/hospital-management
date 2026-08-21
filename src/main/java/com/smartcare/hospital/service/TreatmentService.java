package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Treatment;
import java.util.List;

public interface TreatmentService {
    Treatment recordTreatment(Treatment treatment);
    List<Treatment> getAllTreatments();
    Treatment getTreatmentById(Long id);
    List<Treatment> getTreatmentsByPatientId(Integer patientId);
}