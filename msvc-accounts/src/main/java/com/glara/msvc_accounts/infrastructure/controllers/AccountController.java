package com.glara.msvc_accounts.infrastructure.controllers;

import com.glara.msvc_accounts.dto.AccountDTO;
import com.glara.msvc_accounts.application.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {
    final private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        System.out.println("GET /accounts");
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable String id) {
        return accountService.findById(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<AccountDTO> getTransactionsByAccount(@PathVariable String id) {
        return accountService.findTransactionsByAccount(UUID.fromString(id)).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
