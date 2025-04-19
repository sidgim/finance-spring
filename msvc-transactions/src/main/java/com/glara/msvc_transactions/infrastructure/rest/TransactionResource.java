package com.glara.msvc_transactions.infrastructure.rest;

import com.glara.msvc_transactions.application.services.TransactionService;
import com.glara.msvc_transactions.dto.AccountDTO;
import com.glara.msvc_transactions.dto.TransactionDTO;
import com.glara.msvc_transactions.infrastructure.external.AccountServiceWebClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionResource {


    private final TransactionService transactionService;

    private final AccountServiceWebClient accountServiceWebClient;

    public TransactionResource(TransactionService transactionService, AccountServiceWebClient accountServiceWebClient) {
        this.transactionService = transactionService;
        this.accountServiceWebClient = accountServiceWebClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findByIdTransaction(@PathVariable UUID id) {
        try {
            TransactionDTO result = transactionService.findByIdTransaction(id);
            System.out.println("Transaction found: " + result);
            Optional<AccountDTO> accountDTO = accountServiceWebClient.findById(result.accountId());
            if (accountDTO.isPresent()) {
                // reasigna el nuevo DTO que incluye la cuenta
                result = result.withAccount(accountDTO.get());
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAllTransaction(
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page) {
        try {
            List<TransactionDTO> result = transactionService.findAllTransaction(size, page);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            TransactionDTO saved = transactionService.createTransaction(transactionDTO);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable UUID id, @RequestBody TransactionDTO transactionDTO) {
        try {
            TransactionDTO updated = transactionService.updateTransaction(id, transactionDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> findByAccountId(@PathVariable UUID accountId) {
        try {
            List<TransactionDTO> result = transactionService.findByAccountId(accountId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}