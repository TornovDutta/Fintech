package com.example.fintech.service;

import com.example.fintech.dto.TransferRequest;
import com.example.fintech.model.Account;
import com.example.fintech.model.Transaction;
import com.example.fintech.repository.AccountRepository;
import com.example.fintech.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction transfer(TransferRequest request) {
        Account source = accountRepository.findById(request.getSourceAccountId())
                .orElseThrow(() -> new RuntimeException("Source account not found"));
                
        Account destination = accountRepository.findById(request.getDestinationAccountId())
                .orElseThrow(() -> new RuntimeException("Destination account not found"));
                
        if (!source.getCurrency().equals(destination.getCurrency())) {
            throw new RuntimeException("Cross-currency transfers are not supported yet");
        }
        
        if (source.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        
        // Update balances
        source.setBalance(source.getBalance().subtract(request.getAmount()));
        source.setUpdatedAt(LocalDateTime.now());
        
        destination.setBalance(destination.getBalance().add(request.getAmount()));
        destination.setUpdatedAt(LocalDateTime.now());
        
        accountRepository.save(source);
        accountRepository.save(destination);
        
        // Record transaction
        Transaction transaction = Transaction.builder()
                .sourceAccountId(source.getId())
                .destinationAccountId(destination.getId())
                .amount(request.getAmount())
                .currency(source.getCurrency())
                .transactionType("TRANSFER")
                .status("COMPLETED")
                .timestamp(LocalDateTime.now())
                .build();
                
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAccountTransactions(String accountId) {
        return transactionRepository.findBySourceAccountIdOrDestinationAccountId(accountId, accountId);
    }
}
