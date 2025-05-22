package com.glara.springcloud.msvc.users.application.ports;

import com.glara.springcloud.msvc.users.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    void deleteById(UUID id);
    Optional<User> findByUsername(String username);
}
