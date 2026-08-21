package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.LabTest;
import com.smartcare.hospital.enums.TestStatus;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.repository.LabTestRepository;
import com.smartcare.hospital.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LabTestServiceImpl implements LabTestService {

    private final LabTestRepository labTestRepository;

    @Autowired
    public LabTestServiceImpl(LabTestRepository labTestRepository) {
        this.labTestRepository = labTestRepository;
    }

    @Override
    public LabTest addLabTest(LabTest labTest) {
        if (labTest.getTestDate() == null) {
            labTest.setTestDate(LocalDate.now());
        }
        labTest.setTestStatus(TestStatus.Pending);
        return labTestRepository.save(labTest);
    }

    @Override
    public List<LabTest> getAllLabTests() {
        return labTestRepository.findAll();
    }

    @Override
    public LabTest getLabTestById(Long id) {
        return labTestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lab test record not found with id: " + id));
    }

    @Override
    public LabTest updateLabResult(Long id, String result) {
        LabTest labTest = getLabTestById(id);
        labTest.setTestResult(result);
        labTest.setTestStatus(TestStatus.Completed);
        return labTestRepository.save(labTest);
    }
}