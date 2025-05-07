package com.glara.msvc_accounts.application.services;

import com.glara.msvc_accounts.application.mapper.AccountMapper;
import com.glara.msvc_accounts.domain.entities.Account;
import com.glara.msvc_accounts.infrastructure.client.TransactionServiceClientImpl;
import com.glara.msvc_accounts.infrastructure.repositories.AccountRepository;
import com.glara.springcloud.commons.dto.AccountDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
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
    @CircuitBreaker(name = "transactions", fallbackMethod = "fallbackTransactions")
    public Optional<AccountDTO> findTransactionsByAccount(UUID id) {
        return accountRepository.findByIdAndDeletedFalse(id)
                .map(account -> {
                    var accountDTO = accountMapper.toDTO(account);
                    var transactions = transactionServiceClientImpl.getTransactionsByAccount(id);
                    return accountDTO.withTransactions(transactions);
                });

    }

    public Optional<AccountDTO> fallbackTransactions(UUID id, Throwable t) {
        log.error("Circuit breaker activado para transacciones de cuenta {}: {}", id, t.getMessage());
        return Optional.empty();
    }

}