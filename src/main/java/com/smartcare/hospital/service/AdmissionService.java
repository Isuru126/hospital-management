package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Admission;
import java.util.List;

public interface AdmissionService {
    Admission admitPatient(Admission admission);
    List<Admission> getAllAdmissions();
    Admission getAdmissionById(Long id);
    Admission dischargePatient(Long id);
}