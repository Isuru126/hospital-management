package com.smartcare.hospital.controller;

import com.smartcare.hospital.entity.Admission;
import com.smartcare.hospital.service.AdmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admissions")
public class AdmissionController {

    private final AdmissionService admissionService;

    @Autowired
    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @PostMapping
    public ResponseEntity<Admission> admitPatient(@Valid @RequestBody Admission admission) {
        Admission created = admissionService.admitPatient(admission);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Admission>> getAllAdmissions() {
        return ResponseEntity.ok(admissionService.getAllAdmissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(admissionService.getAdmissionById(id));
    }

    @PutMapping("/{id}/discharge")
    public ResponseEntity<Admission> dischargePatient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(admissionService.dischargePatient(id));
    }
}