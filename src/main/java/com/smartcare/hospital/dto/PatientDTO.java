package com.smartcare.hospital.dto;

import com.smartcare.hospital.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Integer id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid contact number format")
    private String contactNumber;

    private String address;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String bloodGroup;

    private String emergencyContact;
}