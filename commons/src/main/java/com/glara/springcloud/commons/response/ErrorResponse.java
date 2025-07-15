package com.glara.springcloud.commons.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;

/**
 * Standard API response format for error responses
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int status;
    private boolean success;
    private ErrorDetails error;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private ZonedDateTime timestamp;
    
    private String path;

    // Default constructor
    public ErrorResponse() {
        this.success = false;
        this.timestamp = ZonedDateTime.now();
    }

    // Constructor with all fields
    public ErrorResponse(int status, ErrorDetails error, String path) {
        this.status = status;
        this.success = false;
        this.error = error;
        this.timestamp = ZonedDateTime.now();
        this.path = path;
    }

    // Static factory methods for common error responses
    public static ErrorResponse badRequest(String code, String message, String details, String path) {
        return new ErrorResponse(400, new ErrorDetails(code, message, details), path);
    }

    public static ErrorResponse notFound(String code, String message, String details, String path) {
        return new ErrorResponse(404, new ErrorDetails(code, message, details), path);
    }

    public static ErrorResponse conflict(String code, String message, String details, String path) {
        return new ErrorResponse(409, new ErrorDetails(code, message, details), path);
    }

    public static ErrorResponse internalError(String code, String message, String details, String path) {
        return new ErrorResponse(500, new ErrorDetails(code, message, details), path);
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorDetails getError() {
        return error;
    }

    public void setError(ErrorDetails error) {
        this.error = error;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}