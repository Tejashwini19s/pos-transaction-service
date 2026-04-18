package com.example.pos_transaction_service.controller;

import com.example.pos_transaction_service.service.IdempotencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final IdempotencyService idempotencyService;

    public TransactionController(IdempotencyService idempotencyService) {
        this.idempotencyService = idempotencyService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(
            @RequestHeader("Idempotency-Key") String key) {

        return idempotencyService.handle(
                key,
                "/transactions",
                "POST",
                () -> ResponseEntity.ok("Transaction Success")
        );
    }

    
    @GetMapping("/{id}")
    	public ResponseEntity<String> getTransaction(@PathVariable Long id) {
    		return ResponseEntity.ok("Transaction details for " + id);
    	}
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTransaction(
            @PathVariable Long id,
            @RequestHeader("Idempotency-Key") String key) {

        return idempotencyService.handle(
                key,
                "/transactions/" + id,
                "PUT",
                () -> ResponseEntity.ok("Transaction Updated")
        );
    }
    
    }


