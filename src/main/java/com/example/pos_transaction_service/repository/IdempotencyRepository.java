package com.example.pos_transaction_service.repository;

import com.example.pos_transaction_service.entity.Idempotency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdempotencyRepository extends JpaRepository<Idempotency, Long> {

    Optional<Idempotency> findByIdempotencyKeyAndEndpointAndMethod(
            String idempotencyKey,
            String endpoint,
            String method
    );
}