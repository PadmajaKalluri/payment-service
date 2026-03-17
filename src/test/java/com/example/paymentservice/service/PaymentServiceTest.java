package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Set studentUrl manually for testing
        paymentService.studentUrl = "http://localhost:8081/students/get/";
    }

    @Test
    void testGetPaymentById_Found() {
        // Arrange
        Payment payment = new Payment();
        payment.setStudentId(1L);
        payment.setFee(500.0);

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        // Act
        Payment result = paymentService.getPaymentById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getStudentId());
        assertEquals(500.0, result.getFee());
    }

    @Test
    void testGetPaymentById_NotFound() {
        // Arrange
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Payment result = paymentService.getPaymentById(1L);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetPaymentsByStudentId() {
        // Arrange
        Payment payment1 = new Payment();
        payment1.setStudentId(1L);
        Payment payment2 = new Payment();
        payment2.setStudentId(1L);

        when(paymentRepository.findByStudentId(1L)).thenReturn(Arrays.asList(payment1, payment2));

        // Act
        List<Payment> payments = paymentService.getPaymentsByStudentId(1L);

        // Assert
        assertEquals(2, payments.size());
        assertTrue(payments.stream().allMatch(p -> p.getStudentId().equals(1L)));
    }
}