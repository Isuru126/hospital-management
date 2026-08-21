package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Bill;
import com.smartcare.hospital.enums.PaymentMethod;

public interface PaymentService {
    boolean processPayment(Bill bill);
    PaymentMethod getPaymentMethod();
}