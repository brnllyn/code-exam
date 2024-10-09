package com.example.coding_exam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Savings {
    private Long accountNumber;
    private String accountType;
    private Double availableBalance;
}
