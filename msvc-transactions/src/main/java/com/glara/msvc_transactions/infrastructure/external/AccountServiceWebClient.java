package com.glara.msvc_transactions.infrastructure.external;

import com.glara.msvc_transactions.dto.AccountDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceWebClient implements TransactionService {

    private final WebClient.Builder client;

    public AccountServiceWebClient(WebClient.Builder client) {
        this.client = client;
    }

    @Override
    public Optional<AccountDTO> findById(UUID id) {
        return client.build()
                .get()
                .uri("/account/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .blockOptional();
    }
}
