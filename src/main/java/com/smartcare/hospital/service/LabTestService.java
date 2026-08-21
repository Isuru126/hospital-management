package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.LabTest;
import java.util.List;

public interface LabTestService {
    LabTest addLabTest(LabTest labTest);
    List<LabTest> getAllLabTests();
    LabTest getLabTestById(Long id);
    LabTest updateLabResult(Long id, String result);
}