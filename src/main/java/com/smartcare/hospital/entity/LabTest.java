package com.smartcare.hospital.entity;

import com.smartcare.hospital.enums.TestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "lab_tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lab_test_id")
    private Long labTestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(name = "test_date")
    private LocalDate testDate;

    @Column(name = "test_result", columnDefinition = "TEXT")
    private String testResult;

    @Column(name = "technician_name")
    private String technicianName;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_status")
    private TestStatus testStatus;
}