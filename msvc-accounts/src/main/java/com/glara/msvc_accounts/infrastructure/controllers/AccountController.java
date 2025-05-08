package com.glara.msvc_accounts.infrastructure.controllers;

import com.glara.msvc_accounts.application.services.AccountService;
import com.glara.springcloud.commons.dto.AccountDTO;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {
    final private AccountService accountService;

    private final Environment env;

    public AccountController(AccountService accountService, Environment env) {
        this.accountService = accountService;
        this.env = env;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        System.out.println("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable String id) {
        return accountService.findById(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<AccountDTO> getTransactionsByAccount(@PathVariable UUID id) {
        System.out.println("GET /accounts/" + id + "/transactions");
        return accountService.findTransactionsByAccount(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
