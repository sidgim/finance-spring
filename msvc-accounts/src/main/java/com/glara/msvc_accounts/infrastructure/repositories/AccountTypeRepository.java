package com.glara.msvc_accounts.infrastructure.repositories;

import com.glara.msvc_accounts.domain.entities.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, UUID> {
}
