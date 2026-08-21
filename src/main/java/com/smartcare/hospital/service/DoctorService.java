package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor addDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Integer id);
    Doctor updateDoctor(Integer id, Doctor doctor);
    void deleteDoctor(Integer id);
    Doctor assignToDepartment(Integer doctorId, Integer departmentId);
}