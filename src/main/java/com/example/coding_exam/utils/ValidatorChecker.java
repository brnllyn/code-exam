package com.example.coding_exam.utils;

import com.example.coding_exam.dto.Account;

public class ValidatorChecker {

    // Method to check if the required fields are null or empty
    public String validateAccount(Account account) {
        if (account.getCustomerName() == null || account.getCustomerName().trim().isEmpty()) {
            return "Customer name is required";
        }

        if (account.getCustomerMobile() == null || account.getCustomerMobile().trim().isEmpty()) {
            return "Customer mobile is required";
        }

        if (account.getCustomerEmail() == null || account.getCustomerEmail().trim().isEmpty()) {
            return "Customer email is required";
        }

        if (account.getFirstAddress() == null || account.getFirstAddress().trim().isEmpty()) {
            return "First address is required";
        }

        if (account.getAccountType() == null) {
            return "Account type is required";
        }

        // All fields are valid
        return null;
    }
}

