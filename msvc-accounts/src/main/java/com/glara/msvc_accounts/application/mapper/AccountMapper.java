package com.glara.msvc_accounts.application.mapper;

import com.glara.msvc_accounts.dto.AccountDTO;
import com.glara.msvc_accounts.domain.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "accountType.id", target = "accountTypeId")
    AccountDTO toDTO(Account account);

}
