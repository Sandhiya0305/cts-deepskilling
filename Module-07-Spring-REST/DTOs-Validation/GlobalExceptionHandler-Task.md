# Task Description

Create a @ControllerAdvice class for global exception handling. Handle: MethodArgumentNotValidException (return 400 with field error map), ResourceNotFoundException (custom exception, return 404 with message), IllegalArgumentException (return 400), DataIntegrityViolationException (return 409 Conflict), generic Exception (return 500). Return a structured ErrorResponse object with timestamp, status, error, message, path, and details. Use @ExceptionHandler for each exception type. Show how this centralizes error handling across all controllers.
