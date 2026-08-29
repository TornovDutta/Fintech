package com.example.fintech.service;

import com.example.fintech.client.AccountClient;
import com.example.fintech.dto.TransferRequest;
import com.example.fintech.model.Transaction;
import com.example.fintech.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountClient accountClient;
    private final TransactionRepository transactionRepository;

    public Transaction transfer(TransferRequest request) {
        String sourceCurrency = accountClient.getCurrency(request.getSourceAccountId());
        String destCurrency = accountClient.getCurrency(request.getDestinationAccountId());
                
        if (!sourceCurrency.equals(destCurrency)) {
            throw new RuntimeException("Cross-currency transfers are not supported yet");
        }
        
        BigDecimal balance = accountClient.getBalance(request.getSourceAccountId());
        if (balance.compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        
        // Update balances via Feign
        accountClient.updateBalance(request.getSourceAccountId(), request.getAmount().negate());
        accountClient.updateBalance(request.getDestinationAccountId(), request.getAmount());
        
        // Record transaction
        Transaction transaction = Transaction.builder()
                .sourceAccountId(request.getSourceAccountId())
                .destinationAccountId(request.getDestinationAccountId())
                .amount(request.getAmount())
                .currency(sourceCurrency)
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
