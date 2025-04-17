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
        OffsetDateTime createdAt
) {}
