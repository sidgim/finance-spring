package com.glara.msvc_transactions.infrastructure.external;

import com.glara.msvc_transactions.dto.AccountDTO;

import java.util.Optional;
import java.util.UUID;

public interface TransactionService {

    Optional<AccountDTO> findById(UUID id);

}
