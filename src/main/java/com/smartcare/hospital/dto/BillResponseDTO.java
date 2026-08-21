package com.smartcare.hospital.dto;

import com.smartcare.hospital.enums.PaymentMethod;
import com.smartcare.hospital.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillResponseDTO {

    private Long billId;
    private Integer patientId;
    private String patientName;
    private LocalDate billDate;
    private BigDecimal consultationCharge;
    private BigDecimal roomCharge;
    private BigDecimal labCharge;
    private BigDecimal medicineCharge;
    private BigDecimal totalAmount;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
}