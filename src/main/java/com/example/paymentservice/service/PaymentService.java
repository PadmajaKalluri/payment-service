package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository repo;
    private final RestTemplate restTemplate;
    @Value("${studentsserviceurl}")
    public String studentUrl;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
    }

    public Payment makePayment(Payment payment) {
        String url = studentUrl + payment.getStudentId();

        restTemplate.getForObject(url, Object.class);
        payment.setReference(UUID.randomUUID().toString());
        payment.setTransactionDate(LocalDateTime.now());
        payment.setFee(payment.getFee());
        payment.setFeeType(payment.getFeeType());
        payment.setStatus("SUCCESS"); // For demo purposes
        return repo.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Payment> getPaymentsByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }
}