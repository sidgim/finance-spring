package com.glara.msvc_accounts.infrastructure.client;

import com.glara.springcloud.commons.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceClientImpl {

    private final TransactionServiceClient transactionServiceClient;

    public TransactionServiceClientImpl(TransactionServiceClient transactionServiceClient) {
        this.transactionServiceClient = transactionServiceClient;
    }

    public List<TransactionDTO> getTransactionsByAccount(UUID accountId) {
        return transactionServiceClient.getTransactionsByAccount(accountId);
    }
}
