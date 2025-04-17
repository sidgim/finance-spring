package com.glara.msvc_accounts.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public record AccountDTO(
        UUID id,
        String name,
        BigDecimal currentBalance,
        String accountTypeId,
        UUID userId,
        boolean deleted,
        List<TransactionDTO> transactions
) {
    public AccountDTO withTransactions(List<TransactionDTO> transactions) {
        return new AccountDTO(id, name, currentBalance, accountTypeId, userId, deleted, transactions);
    }
}


