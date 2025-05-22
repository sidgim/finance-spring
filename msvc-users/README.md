# Standardized API Response Structure

This project implements a standardized API response structure for both successful and error responses. This ensures consistency across all API endpoints and makes it easier for clients to handle responses.

## Successful Response Structure

```json
{
  "status": 200,
  "success": true,
  "message": "Operación exitosa",
  "data": {
    "id": "7e206713-1418-406a-9a6f-76fb2356051f",
    "username": "gaston.dev",
    "email": "gaston@example.com"
  },
  "timestamp": "2025-05-22T21:55:00Z"
}
```

## Error Response Structure

```json
{
  "status": 409,
  "success": false,
  "error": {
    "code": "USR-001",
    "message": "El correo electrónico ya existe.",
    "details": "Violación de restricción única en la base de datos"
  },
  "timestamp": "2025-05-22T21:55:00Z",
  "path": "/user"
}
```

## Implementation Details

### Response Classes

- `ApiResponse<T>`: Generic class for successful responses
- `ErrorResponse`: Class for error responses
- `ErrorDetails`: Class for detailed error information

### Exception Handling

- `BaseException`: Abstract base class for all custom exceptions
- `ResourceNotFoundException`: Thrown when a requested resource is not found
- `DuplicateResourceException`: Thrown when there's a conflict with an existing resource
- `ValidationException`: Thrown when input validation fails
- `GlobalExceptionHandler`: Controller advice that handles exceptions and converts them to standardized error responses

## Usage Examples

### Returning a Successful Response

```java
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<User>> getById(@PathVariable UUID id) {
    User user = userService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", id));
    
    ApiResponse<User> response = ApiResponse.success("Usuario encontrado", user);
    return ResponseEntity.ok(response);
}
```

### Throwing Exceptions (will be converted to error responses)

```java
// In service layer
if (!repository.findById(id).isPresent()) {
    throw new ResourceNotFoundException("User", id);
}

// For duplicate resources
if (repository.findByEmail(email).isPresent()) {
    throw DuplicateResourceException.emailConflict(email);
}
```

## Error Codes

The system uses prefixed error codes to categorize different types of errors:

- `RSC-xxx`: Resource-related errors (not found, etc.)
- `DUP-xxx`: Duplicate resource errors
- `VAL-xxx`: Validation errors
- `DB-xxx`: Database-related errors
- `SRV-xxx`: General server errors