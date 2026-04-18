package com.example.pos_transaction_service.service;

import com.example.pos_transaction_service.entity.Idempotency;
import com.example.pos_transaction_service.repository.IdempotencyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class IdempotencyService {

    private final IdempotencyRepository repository;

    public IdempotencyService(IdempotencyRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<String> handle(
            String key,
            String endpoint,
            String method,
            Supplier<ResponseEntity<String>> action) {

        Optional<Idempotency> existing =
                repository.findByIdempotencyKeyAndEndpointAndMethod(key, endpoint, method);

        if (existing.isPresent()) {
            Idempotency record = existing.get();

            return ResponseEntity
                    .status(record.getStatusCode())
                    .body(record.getResponse());
        }

        ResponseEntity<String> response = action.get();

        Idempotency record = new Idempotency();
        record.setIdempotencyKey(key);
        record.setEndpoint(endpoint);
        record.setMethod(method);
        record.setResponse(response.getBody());
        record.setStatusCode(response.getStatusCode().value());

        repository.save(record);

        return response;
    }
}