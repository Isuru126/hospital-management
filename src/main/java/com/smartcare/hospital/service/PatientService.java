package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Patient;
import java.util.List;

public interface PatientService {
    Patient registerPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Integer id);
    Patient updatePatient(Integer id, Patient patient);
    void deletePatient(Integer id);
    List<Patient> searchPatients(String query);
}