package com.glara.msvc_transactions.application.services;

import com.glara.msvc_transactions.application.mapper.TransactionMapper;
import com.glara.msvc_transactions.dto.TransactionDTO;
import com.glara.msvc_transactions.domain.entities.Transaction;
import com.glara.msvc_transactions.infrastructure.repository.TransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionMapper mapper;

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<TransactionDTO> findAllTransaction(int size, int page) {
        Pageable pg = PageRequest.of(page, size);

        return transactionRepository.findAll(pg).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public TransactionDTO findByIdTransaction(UUID id) throws Exception {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Transaction not found"));
        return mapper.toDto(transaction);
    }

    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = mapper.toEntity(transactionDTO);
        transaction.setId(UUID.randomUUID());
        Transaction saved = transactionRepository.save(transaction);
        return mapper.toDto(saved);
    }

    @Transactional
    public TransactionDTO updateTransaction(UUID id, TransactionDTO transactionDTO) throws Exception {
        if (!transactionRepository.existsById(id)) {
            throw new Exception("Transaction not found");
        }
        Transaction transaction = mapper.toEntity(transactionDTO);
        transaction.setId(id);
        Transaction updated = transactionRepository.save(transaction);
        return mapper.toDto(updated);
    }

    @Transactional
    public void deleteTransaction(UUID id) throws Exception {
        if (!transactionRepository.existsById(id)) {
            throw new Exception("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TransactionDTO> findByAccountId(UUID accountId) throws Exception {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        if (transactions.isEmpty()) {
            throw new Exception("No transactions found for account ID: " + accountId);
        }
        return transactions.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}