package com.glara.springcloud.msvc.users.application.services;

import com.glara.springcloud.msvc.users.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User create(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    User update(UUID id, User user);
    void delete(UUID id);
}
