package com.smartcare.hospital.repository;

import com.smartcare.hospital.entity.Bill;
import com.smartcare.hospital.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    /**
     * Finds all billing records associated with a specific patient.
     */
    List<Bill> findByPatientId(Integer patientId);

    /**
     * Finds bills filtered by payment status (e.g., Pending, Paid, Partially_Paid).
     */
    List<Bill> findByPaymentStatus(PaymentStatus paymentStatus);
}