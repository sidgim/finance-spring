package com.glara.msvc_accounts.application.mapper;

import com.glara.msvc_accounts.domain.entities.AccountType;
import com.glara.springcloud.commons.dto.AccountTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    AccountTypeDTO toDTO(AccountType accountType);

    AccountType toEntity(AccountTypeDTO dto);
}
