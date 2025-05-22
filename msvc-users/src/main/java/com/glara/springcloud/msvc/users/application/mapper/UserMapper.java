package com.glara.springcloud.msvc.users.application.mapper;


import com.glara.springcloud.commons.dto.UserDTO;
import com.glara.springcloud.msvc.users.application.dto.CreateUserRequest;
import com.glara.springcloud.msvc.users.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    User toEntity(CreateUserRequest dto);
}
