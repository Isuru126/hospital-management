package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Department;
import com.smartcare.hospital.entity.Doctor;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.repository.DepartmentRepository;
import com.smartcare.hospital.repository.DoctorRepository;
import com.smartcare.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    public Doctor updateDoctor(Integer id, Doctor details) {
        Doctor doctor = getDoctorById(id);
        doctor.setFullName(details.getFullName());
        doctor.setContactNumber(details.getContactNumber());
        doctor.setAddress(details.getAddress());
        doctor.setQualification(details.getQualification());
        doctor.setSpecialization(details.getSpecialization());
        doctor.setConsultationFee(details.getConsultationFee());
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Integer id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }

    @Override
    public Doctor assignToDepartment(Integer doctorId, Integer departmentId) {
        Doctor doctor = getDoctorById(doctorId);
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
        doctor.setDepartment(department);
        return doctorRepository.save(doctor);
    }
}