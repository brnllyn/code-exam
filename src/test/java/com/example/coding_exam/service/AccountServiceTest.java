package com.example.coding_exam.service;

import com.example.coding_exam.dto.Account;
import com.example.coding_exam.dto.ResponsePayload;
import com.example.coding_exam.dto.Savings;
import com.example.coding_exam.repository.AccountRepository;
import com.example.coding_exam.utils.ValidatorChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ValidatorChecker validatorChecker;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void given_dummy_account_when_save_then_verify() {
        Account dummyAccount = dummyAccount();

        when(validatorChecker.validateAccount(any(Account.class))).thenReturn(null);
        when(accountRepository.save(any(Account.class))).thenReturn(dummyAccount);

        accountService.saveAccount(dummyAccount);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void given_dummy_account_without_email_when_save_then_assert_details() {
        when(validatorChecker.validateAccount(any(Account.class))).thenReturn("Email is required field");

        ResponseEntity<?> response = accountService.saveAccount(dummyAccountMissingEmail());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ResponsePayload payload = (ResponsePayload) response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), payload.getStatusCode());
        assertEquals("Email is required field", payload.getStatusDescription());
    }


    @Test
    void given_dummy_account_when_fetch_by_id_then_assert() {
        Account account = dummyAccount();
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        ResponseEntity<?> response = accountService.findAccountById(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        ResponsePayload payload = (ResponsePayload) response.getBody();
        assertEquals(HttpStatus.FOUND.value(), payload.getStatusCode());
        assertEquals("Customer Account found", payload.getStatusDescription());
        assertEquals(account.getCustomerName(), payload.getCustomerName());
        assertEquals(account.getCustomerMobile(), payload.getCustomerMobile());
        assertEquals(account.getCustomerEmail(), payload.getCustomerEmail());
    }

    @Test
    public void given_dummy_account_when_account_not_found_then_assert() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> response = accountService.findAccountById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        ResponsePayload payload = (ResponsePayload) response.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), payload.getStatusCode());
        assertEquals("Customer account not found", payload.getStatusDescription());
    }

    public static Account dummyAccount() {
        Account dummyAccount = new Account();
        dummyAccount.setId(1L);
        dummyAccount.setCustomerName("John Doe");
        dummyAccount.setCustomerMobile("09081234567");
        dummyAccount.setCustomerEmail("johndoe@example.com");
        dummyAccount.setFirstAddress("123 Main St, City, Country");
        dummyAccount.setSecondAddress("Sample address");
        return dummyAccount;
    }

    public static Account dummyAccountMissingEmail() {
        Account dummyAccount = new Account();
        dummyAccount.setId(1L);
        dummyAccount.setCustomerName("John Doe");
        dummyAccount.setCustomerMobile("09081234567");
        dummyAccount.setCustomerEmail("");
        dummyAccount.setFirstAddress("123 Main St, City, Country");
        dummyAccount.setSecondAddress("Sample address");
        return dummyAccount;
    }
}
