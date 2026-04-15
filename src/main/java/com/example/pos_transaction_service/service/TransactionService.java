package com.example.pos_transaction_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos_transaction_service.entity.Transaction;
import com.example.pos_transaction_service.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {

        double total = (transaction.getPrice() * transaction.getQuantity())
                - transaction.getDiscount()
                + transaction.getTax();

        transaction.setTotalAmount(total);
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}