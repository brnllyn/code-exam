package com.example.coding_exam.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;

    @Column(name = "customer_mobile", nullable = false, length = 20)
    private String customerMobile;

    @Column(name = "customer_email", nullable = false, length = 50)
    private String customerEmail;

    @Column(name = "first_address", nullable = false, length = 100)
    private String firstAddress;

    @Column(name = "second_address", nullable = false, length = 100)
    private String secondAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    public enum AccountType {
        S,
        C
    }
}
