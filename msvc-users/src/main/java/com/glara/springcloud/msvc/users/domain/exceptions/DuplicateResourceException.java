package com.glara.springcloud.msvc.users.domain.exceptions;

/**
 * Exception thrown when there's a conflict with an existing resource
 */
public class DuplicateResourceException extends BaseException {
    private static final String ERROR_CODE_PREFIX = "DUP";

    public DuplicateResourceException(String resourceType, String field, String value) {
        super(
            String.format("%s with %s '%s' already exists", resourceType, field, value),
            ERROR_CODE_PREFIX + "-001",
            String.format("Attempted to create a %s with a %s that is already in use", resourceType.toLowerCase(), field)
        );
    }

    // Specific constructor for user email conflict
    public static DuplicateResourceException emailConflict(String email) {
        return new DuplicateResourceException("User", "email", email);
    }

    // Specific constructor for username conflict
    public static DuplicateResourceException usernameConflict(String username) {
        return new DuplicateResourceException("User", "username", username);
    }
}