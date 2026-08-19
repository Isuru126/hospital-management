package com.smartcare.hospital.controller;

import com.smartcare.hospital.entity.Bill;
import com.smartcare.hospital.enums.PaymentMethod;
import com.smartcare.hospital.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Bill> generateBill(@Valid @RequestBody Bill bill) {
        Bill created = billService.generateBill(bill);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Bill> processPayment(
            @PathVariable("id") Long id,
            @RequestParam("method") PaymentMethod method) {
        return ResponseEntity.ok(billService.processPayment(id, method));
    }
}