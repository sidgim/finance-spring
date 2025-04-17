package com.glara.msvc_transactions.infrastructure.repository;

import com.glara.msvc_transactions.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByAccountId(UUID accountId);

}
