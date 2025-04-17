package com.glara.msvc_accounts.infrastructure.external;

import com.glara.msvc_accounts.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "transaction-service", url = "http://localhost:8000/transaction")
public interface TransactionServiceClient {
    @GetMapping("/account/{id}")
    List<TransactionDTO> getTransactionsByAccount(@PathVariable UUID id);

}
