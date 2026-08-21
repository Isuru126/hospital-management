package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Bill;
import com.smartcare.hospital.enums.PaymentMethod;
import com.smartcare.hospital.enums.PaymentStatus;
import com.smartcare.hospital.exception.BadRequestException;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.repository.BillRepository;
import com.smartcare.hospital.service.BillService;
import com.smartcare.hospital.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final List<PaymentService> paymentServices;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, List<PaymentService> paymentServices) {
        this.billRepository = billRepository;
        this.paymentServices = paymentServices;
    }

    @Override
    public Bill generateBill(Bill bill) {
        if (bill.getBillDate() == null) {
            bill.setBillDate(LocalDate.now());
        }

        BigDecimal total = BigDecimal.ZERO;
        if (bill.getConsultationCharge() != null) total = total.add(bill.getConsultationCharge());
        if (bill.getRoomCharge() != null) total = total.add(bill.getRoomCharge());
        if (bill.getLabCharge() != null) total = total.add(bill.getLabCharge());
        if (bill.getMedicineCharge() != null) total = total.add(bill.getMedicineCharge());

        bill.setTotalAmount(total);
        bill.setPaymentStatus(PaymentStatus.Pending);
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
    }

    @Override
    public Bill processPayment(Long id, PaymentMethod method) {
        Bill bill = getBillById(id);

        PaymentService paymentService = paymentServices.stream()
                .filter(s -> s.getPaymentMethod() == method)
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Unsupported payment method: " + method));

        boolean success = paymentService.processPayment(bill);
        if (success) {
            bill.setPaymentMethod(method);
            bill.setPaymentStatus(PaymentStatus.Paid);
            return billRepository.save(bill);
        } else {
            throw new BadRequestException("Payment processing failed.");
        }
    }
}