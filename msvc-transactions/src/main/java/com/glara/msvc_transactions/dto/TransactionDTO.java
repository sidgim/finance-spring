package com.glara.msvc_transactions.dto;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionDTO(
        UUID id,
        String description,
        UUID accountId,
        UUID subcategoryId,
        UUID vendorId,
        TransactionType type,
        Double amount,
        OffsetDateTime createdAt,
        int port,
        AccountDTO account
) {
    public TransactionDTO withAccount(AccountDTO account) {
        System.out.println("TransactionDTO.withAccount" + account);
        return new TransactionDTO(
                this.id,
                this.description,
                this.accountId,
                this.subcategoryId,
                this.vendorId,
                this.type,
                this.amount,
                this.createdAt,
                this.port,
                account
        );
    }
}
