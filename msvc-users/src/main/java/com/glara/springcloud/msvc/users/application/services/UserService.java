package com.glara.springcloud.msvc.users.application.services;

import com.glara.springcloud.msvc.users.domain.entities.User;

import java.util.Optional;

public interface UserService {
    User create(User user);
    Optional<User> findById(Long id);
    Iterable<User> findAll();
    User update(Long id, User user);
    void delete(Long id);
}
