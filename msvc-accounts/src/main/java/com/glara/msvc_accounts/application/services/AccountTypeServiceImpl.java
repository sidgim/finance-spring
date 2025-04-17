package com.glara.msvc_accounts.application.services;

import com.glara.msvc_accounts.application.mapper.AccountTypeMapper;
import com.glara.msvc_accounts.dto.AccountTypeDTO;
import com.glara.msvc_accounts.infrastructure.repositories.AccountTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeMapper accountTypeMapper;

    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository, AccountTypeMapper accountTypeMapper) {
        this.accountTypeRepository = accountTypeRepository;
        this.accountTypeMapper = accountTypeMapper;
    }

    @Override
    public Optional<AccountTypeDTO> getAccountType(UUID id) {
        return accountTypeRepository.findById(id).map(accountTypeMapper::toDTO);
    }

    @Override
    public Optional<List<AccountTypeDTO>> findAllAccountTypes() {
        return Optional.empty();
    }

    @Override
    public Optional<AccountTypeDTO> createAccountType(AccountTypeDTO accountTypeDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<AccountTypeDTO> updateAccountType(AccountTypeDTO accountTypeDTO, UUID id) {
        return Optional.empty();
    }

    @Override
    public Void deleteAccountTypeById(UUID id) {
        return null;
    }
}
