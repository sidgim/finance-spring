package com.glara.msvc_accounts.application.services;

import com.glara.msvc_accounts.dto.AccountTypeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountTypeService {

    Optional<AccountTypeDTO> getAccountType(UUID id);

    Optional<List<AccountTypeDTO>> findAllAccountTypes();

    Optional<AccountTypeDTO> createAccountType(AccountTypeDTO accountTypeDTO);

    Optional<AccountTypeDTO> updateAccountType(AccountTypeDTO accountTypeDTO, UUID id);

    Void deleteAccountTypeById(UUID id);
}
