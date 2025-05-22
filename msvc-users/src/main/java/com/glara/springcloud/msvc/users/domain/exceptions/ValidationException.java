package com.glara.springcloud.msvc.users.domain.exceptions;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Exception thrown when input validation fails
 */
public class ValidationException extends BaseException {
    private static final String ERROR_CODE_PREFIX = "VAL";
    private final Map<String, String> errors;

    public ValidationException(String message, Map<String, String> errors) {
        super(
            message,
            ERROR_CODE_PREFIX + "-001",
            formatErrors(errors)
        );
        this.errors = errors;
    }

    private static String formatErrors(Map<String, String> errors) {
        return errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}