package com.example.fintech.repository;

import com.example.fintech.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findBySourceAccountIdOrDestinationAccountId(String sourceId, String destinationId);
}
