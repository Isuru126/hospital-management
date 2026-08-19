package com.smartcare.hospital.controller;

import com.smartcare.hospital.entity.LabTest;
import com.smartcare.hospital.service.LabTestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lab-tests")
public class LabTestController {

    private final LabTestService labTestService;

    @Autowired
    public LabTestController(LabTestService labTestService) {
        this.labTestService = labTestService;
    }

    @PostMapping
    public ResponseEntity<LabTest> addLabTest(@Valid @RequestBody LabTest labTest) {
        LabTest created = labTestService.addLabTest(labTest);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LabTest>> getAllLabTests() {
        return ResponseEntity.ok(labTestService.getAllLabTests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabTest> getLabTestById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(labTestService.getLabTestById(id));
    }

    @PutMapping("/{id}/result")
    public ResponseEntity<LabTest> updateLabResult(
            @PathVariable("id") Long id,
            @RequestParam("result") String result) {
        return ResponseEntity.ok(labTestService.updateLabResult(id, result));
    }
}