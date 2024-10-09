package com.example.coding_exam.controller;

import com.example.coding_exam.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.coding_exam.service.AccountService;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @GetMapping("/account/{Id}")
    public ResponseEntity<?> findAccount(@PathVariable Long Id) {
        return accountService.findAccountById(Id);
    }
}
