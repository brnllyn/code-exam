package com.example.coding_exam.service;

import com.example.coding_exam.dto.Account;
import com.example.coding_exam.dto.ResponsePayload;
import com.example.coding_exam.dto.Savings;
import com.example.coding_exam.repository.AccountRepository;
import com.example.coding_exam.utils.ValidatorChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    ValidatorChecker validator = new ValidatorChecker();
    public ResponseEntity<?> saveAccount(Account account) {
        String validateError = validator.validateAccount(account);
        Account createAccount = accountRepository.save(account);

        if (validateError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePayload(
                    null,
                    HttpStatus.BAD_REQUEST.value(),
                    validateError
            ));
        } else {
            ResponsePayload responsePayload = new ResponsePayload(
                    createAccount.getId(),
                    createAccount.getCustomerName(),
                    createAccount.getCustomerMobile(),
                    createAccount.getCustomerEmail(),
                    createAccount.getFirstAddress(),
                    createAccount.getSecondAddress(),
                    null,
                    HttpStatus.CREATED.value(),
                    "Account created"
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(responsePayload);
        }
    }

    public ResponseEntity<?> findAccountById(Long id) {
        Optional<Account> fetchAccount = accountRepository.findById(id);

        if (!fetchAccount.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePayload(
                    null,
                    HttpStatus.NOT_FOUND.value(),
                    "Customer account not found"
            ));
        }

        Account account = fetchAccount.get();
        Savings savingsAccount = new Savings(1L, "S", 500.0); // You may want to fetch this from the DB

        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponsePayload(
                account.getId(),
                account.getCustomerName(),
                account.getCustomerMobile(),
                account.getCustomerEmail(),
                account.getFirstAddress(),
                account.getSecondAddress(),
                Arrays.asList(savingsAccount),
                HttpStatus.FOUND.value(),
                "Customer Account found"
        ));
    }
}
