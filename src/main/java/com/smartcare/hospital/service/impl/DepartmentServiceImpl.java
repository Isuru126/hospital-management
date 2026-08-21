package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Department;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.repository.DepartmentRepository;
import com.smartcare.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Override
    public Department updateDepartment(Integer id, Department details) {
        Department department = getDepartmentById(id);
        department.setName(details.getName());
        department.setLocation(details.getLocation());
        department.setHeadDoctor(details.getHeadDoctor());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}