package com.example.pos_transaction_service.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pos_transaction_service.entity.Transaction;
import com.example.pos_transaction_service.service.TransactionService;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction( @Valid @RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
    
    @GetMapping("/{id}")
    	public Transaction getTransactionById(@PathVariable Long id) {
    		return transactionService.getTransactionById(id);
    	}
    
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id,
                                        @Valid @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }
    
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteTransaction(id);
    }
    
    }


