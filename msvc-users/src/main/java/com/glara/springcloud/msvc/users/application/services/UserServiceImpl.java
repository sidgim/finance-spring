package com.glara.springcloud.msvc.users.application.services;

import com.glara.springcloud.msvc.users.application.ports.UserRepositoryPort;
import com.glara.springcloud.msvc.users.domain.entities.User;
import com.glara.springcloud.msvc.users.domain.exceptions.DuplicateResourceException;
import com.glara.springcloud.msvc.users.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepositoryPort repo;

    public UserServiceImpl(UserRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public User create(User user) {
        // Check if username already exists
        repo.findByUsername(user.getUsername()).ifPresent(existingUser -> {
            throw DuplicateResourceException.usernameConflict(user.getUsername());
        });

        // Save the user
        return repo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return repo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User update(UUID id, User user) {
        // Check if user exists
        if (!repo.findById(id).isPresent()) {
            throw new ResourceNotFoundException("User", id);
        }

        // Check if username is being changed and already exists
        Optional<User> existingUserWithUsername = repo.findByUsername(user.getUsername());
        if (existingUserWithUsername.isPresent() && !existingUserWithUsername.get().getId().equals(id)) {
            throw DuplicateResourceException.usernameConflict(user.getUsername());
        }

        user.setId(id);
        return repo.save(user);
    }

    @Override
    public void delete(UUID id) {
        // Check if user exists
        if (!repo.findById(id).isPresent()) {
            throw new ResourceNotFoundException("User", id);
        }

        repo.deleteById(id);
    }
}
