package com.glara.msvc_accounts.infrastructure.client;

import com.glara.springcloud.commons.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "msvc-transactions", path = "/transaction")
public interface TransactionServiceClient {
    @GetMapping("/account/{id}")
    List<TransactionDTO> getTransactionsByAccount(@PathVariable UUID id);
}
