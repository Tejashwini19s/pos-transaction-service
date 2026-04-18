package com.example.pos_transaction_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos_transaction_service.dto.TransactionRequest;
import com.example.pos_transaction_service.entity.Transaction;
import com.example.pos_transaction_service.repository.TransactionRepository;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // ✅ Existing logic
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

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction existing = transactionRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setProductName(updatedTransaction.getProductName());
            existing.setQuantity(updatedTransaction.getQuantity());
            existing.setPrice(updatedTransaction.getPrice());
            existing.setDiscount(updatedTransaction.getDiscount());
            existing.setTax(updatedTransaction.getTax());
            existing.setPaymentMethod(updatedTransaction.getPaymentMethod());

            double total = (updatedTransaction.getPrice() * updatedTransaction.getQuantity())
                    - updatedTransaction.getDiscount()
                    + updatedTransaction.getTax();

            existing.setTotalAmount(total);

            return transactionRepository.save(existing);
        }
        return null;
    }

    public String deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return "Transaction deleted successfully";
        }
        return "Transaction not found";
    }

    // ✅ NEW (for idempotency controller)
    public String processTransaction(TransactionRequest request) {

        Transaction transaction = new Transaction();
        
        //mapping request -> entity 
        transaction.setProductName(request.getDescription());
        transaction.setQuantity(1);
        transaction.setPrice(request.getAmount());
        transaction.setDiscount(0.0);
        transaction.setTax(0.0);
        transaction.setPaymentMethod(request.getCurrency());

        Transaction saved = saveTransaction(transaction);

        return "Transaction successful with ID: " + saved.getId();
    }
}

	