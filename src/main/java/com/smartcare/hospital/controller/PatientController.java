package com.smartcare.hospital.controller;

import com.smartcare.hospital.entity.Patient;
import com.smartcare.hospital.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Patient Management operations[cite: 1].
 */
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Registers a new patient.
     */
    @PostMapping
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody Patient patient) {
        Patient createdPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    /**
     * Retrieves all registered patients[cite: 1].
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    /**
     * Fetches details of a specific patient by ID[cite: 1].
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    /**
     * Updates an existing patient record[cite: 1].
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer id, @Valid @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(id, patient));
    }

    /**
     * Deletes a patient record by ID[cite: 1].
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient record deleted successfully.");
    }

    /**
     * Searches patients by full name or contact number[cite: 1, 2].
     */
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam("query") String query) {
        return ResponseEntity.ok(patientService.searchPatients(query));
    }
}