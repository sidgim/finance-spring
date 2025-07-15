package com.glara.springcloud.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Detailed error information for API error responses
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {
    private String code;
    private String message;
    private String details;

    // Default constructor
    public ErrorDetails() {
    }

    // Constructor with all fields
    public ErrorDetails(String code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    // Getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}