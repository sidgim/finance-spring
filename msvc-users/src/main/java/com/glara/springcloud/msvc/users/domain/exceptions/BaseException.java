package com.glara.springcloud.msvc.users.domain.exceptions;

/**
 * Base exception class for all application exceptions
 */
public abstract class BaseException extends RuntimeException {
    private final String errorCode;
    private final String details;

    protected BaseException(String message, String errorCode, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    protected BaseException(String message, String errorCode, String details, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDetails() {
        return details;
    }
}