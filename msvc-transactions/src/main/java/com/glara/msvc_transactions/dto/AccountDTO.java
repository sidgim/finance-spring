package com.glara.msvc_transactions.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDTO(
        UUID id,
        String name,
        BigDecimal currentBalance,
        String accountTypeId,
        UUID userId,
        boolean deleted
) {
}

