package com.smartcare.hospital.repository;

import com.smartcare.hospital.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    /**
     * Retrieves department details by unique department name.
     */
    Optional<Department> findByName(String name);
}