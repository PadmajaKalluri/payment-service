
package com.example.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studentId;
    private double fee;
    private String feeType;
    private String cardNumber;
    private String cardType;
    private String reference;
    private String status; // SUCCESS/FAILED
    private LocalDateTime transactionDate;
}