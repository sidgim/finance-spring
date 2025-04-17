package com.glara.msvc_accounts.infrastructure.controllers;

import com.glara.msvc_accounts.application.services.AccountTypeService;
import com.glara.msvc_accounts.dto.AccountTypeDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account-type")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTypeDTO> getAccountTypeById(@PathParam("id") UUID id) {
        return accountTypeService.getAccountType(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AccountTypeDTO>> getAllAccountTypes() {
        return accountTypeService.findAllAccountTypes()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccountTypeDTO> createAccountType(AccountTypeDTO accountTypeDTO) {
        return accountTypeService.createAccountType(accountTypeDTO)
                .map(accountType -> ResponseEntity.created(URI.create("/account-type/" + accountType.id())).body(accountType))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountTypeDTO> updateAccountType(@PathParam("id") UUID id, AccountTypeDTO accountTypeDTO) {
        return accountTypeService.updateAccountType(accountTypeDTO, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountTypeById(@PathParam("id") UUID id) {
        return ResponseEntity.ok(accountTypeService.deleteAccountTypeById(id));
    }
}
