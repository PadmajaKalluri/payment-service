package com.example.paymentservice.controller;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.dto.PaymentRequest;
import com.example.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService service;
    public PaymentController(PaymentService service){ this.service = service; }

    @PostMapping("/processPayment/v1")
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.status(201).body(service.makePayment(paymentRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id){
        Payment p = service.getPaymentById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public List<Payment> getPaymentsByStudent(@PathVariable Long studentId){
        return service.getPaymentsByStudentId(studentId);
    }
}
