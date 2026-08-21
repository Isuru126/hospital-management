package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Bill;
import com.smartcare.hospital.enums.PaymentMethod;
import com.smartcare.hospital.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class CashPaymentServiceImpl implements PaymentService {

    @Override
    public boolean processPayment(Bill bill) {
        // Physical cashier verification logic
        return true;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.Cash;
    }
}