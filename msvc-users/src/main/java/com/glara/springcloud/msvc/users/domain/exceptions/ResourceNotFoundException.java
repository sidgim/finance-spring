package com.glara.springcloud.msvc.users.domain.exceptions;

import java.util.UUID;

/**
 * Exception thrown when a requested resource is not found
 */
public class ResourceNotFoundException extends BaseException {
    private static final String ERROR_CODE_PREFIX = "RSC";

    public ResourceNotFoundException(String resourceType, UUID id) {
        super(
            String.format("%s with id %s not found", resourceType, id),
            ERROR_CODE_PREFIX + "-001",
            String.format("The requested %s resource with id %s does not exist", resourceType.toLowerCase(), id)
        );
    }

    public ResourceNotFoundException(String resourceType, String identifier, String value) {
        super(
            String.format("%s with %s '%s' not found", resourceType, identifier, value),
            ERROR_CODE_PREFIX + "-001",
            String.format("The requested %s resource with %s '%s' does not exist", resourceType.toLowerCase(), identifier, value)
        );
    }
}