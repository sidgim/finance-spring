package com.glara.msvc_accounts.infrastructure.repositories;

import com.glara.msvc_accounts.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

    Optional<Account> findByIdAndDeletedFalse(UUID id);

    List<Account> findByUserIdAndDeletedFalse(UUID userId);

    Account findByNameAndUserIdAndDeletedFalse(String name, UUID userId);

    Account findByNameAndUserIdAndDeletedTrue(String name, UUID userId);
}
