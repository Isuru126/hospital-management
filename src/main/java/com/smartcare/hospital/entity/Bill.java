package com.smartcare.hospital.entity;

import com.smartcare.hospital.enums.PaymentMethod;
import com.smartcare.hospital.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "bill_date")
    private LocalDate billDate;

    @Column(name = "consultation_charge")
    private BigDecimal consultationCharge;

    @Column(name = "room_charge")
    private BigDecimal roomCharge;

    @Column(name = "lab_charge")
    private BigDecimal labCharge;

    @Column(name = "medicine_charge")
    private BigDecimal medicineCharge;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
}