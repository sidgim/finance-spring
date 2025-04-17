package com.glara.msvc_accounts.application.services;

import com.glara.msvc_accounts.application.mapper.AccountMapper;
import com.glara.msvc_accounts.dto.AccountDTO;
import com.glara.msvc_accounts.domain.entities.Account;
import com.glara.msvc_accounts.infrastructure.external.TransactionServiceClientImpl;
import com.glara.msvc_accounts.infrastructure.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionServiceClientImpl transactionServiceClientImpl;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, TransactionServiceClientImpl transactionServiceClientImpl) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.transactionServiceClientImpl = transactionServiceClientImpl;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> findByUserId(UUID userId) {
        return accountRepository.findByUserIdAndDeletedFalse(userId)
                .stream()
                .map(accountMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> findAll() {
        Iterable<Account> accounts = accountRepository.findAll();
        return StreamSupport.stream(accounts.spliterator(), false)
                .map(accountMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountDTO> findById(UUID id) {
        return accountRepository.findByIdAndDeletedFalse(id)
                .map(accountMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountDTO> findTransactionsByAccount(UUID id) {
        return accountRepository.findByIdAndDeletedFalse(id)
                .map(account -> {
                    var accountDTO = accountMapper.toDTO(account);
                    var transactions = transactionServiceClientImpl.getTransactionsByAccount(id);
                    return accountDTO.withTransactions(transactions);
                });

    }
}