package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Bill;
import com.smartcare.hospital.enums.PaymentMethod;
import java.util.List;

public interface BillService {
    Bill generateBill(Bill bill);
    List<Bill> getAllBills();
    Bill getBillById(Long id);
    Bill processPayment(Long id, PaymentMethod method);
}