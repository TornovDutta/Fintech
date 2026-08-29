package com.example.fintech.service;

import com.example.fintech.dto.AccountRequest;
import com.example.fintech.model.Account;
import com.example.fintech.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(AccountRequest request) {
        Account account = Account.builder()
                .customerName(request.getCustomerName())
                .accountNumber(generateAccountNumber())
                .balance(request.getInitialBalance())
                .currency(request.getCurrency())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        return accountRepository.save(account);
    }

    public Account getAccount(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
    }
    
    @Transactional
    public void updateBalance(String id, java.math.BigDecimal amount) {
        Account account = getAccount(id);
        account.setBalance(account.getBalance().add(amount));
        account.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(account);
    }
}
