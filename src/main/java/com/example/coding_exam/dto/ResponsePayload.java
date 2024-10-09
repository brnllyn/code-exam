package com.example.coding_exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePayload {
    private Long customerNumber;
    private int statusCode;
    private String statusDescription;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String address1;
    private String address2;
    private List<Savings> savings;
    private int transactionStatusCode;
    private String transactionStatusDescription;

    public ResponsePayload(Long customerNumber, int statusCode, String statusDescription) {
        this.customerNumber = customerNumber;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public ResponsePayload(Long customerNumber, String customerName, String customerMobile,
                           String customerEmail, String address1, String address2,
                           List<Savings> savings, int transactionStatusCode,
                           String transactionStatusDescription) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.address1 = address1;
        this.address2 = address2;
        this.savings = savings;
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusDescription = transactionStatusDescription;
        this.statusCode = transactionStatusCode;
        this.statusDescription = transactionStatusDescription;
    }

}
