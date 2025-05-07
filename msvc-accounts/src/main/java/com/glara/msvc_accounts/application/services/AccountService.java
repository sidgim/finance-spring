package com.glara.msvc_accounts.application.services;


import com.glara.springcloud.commons.dto.AccountDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    List<AccountDTO> findByUserId(UUID userId);
    List<AccountDTO> findAll();
    Optional<AccountDTO> findById(UUID id);
    Optional<AccountDTO> findTransactionsByAccount(UUID id);
    //AccountDTO createAccount(AccountDTO account);


}
