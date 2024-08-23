package com.lecspace.ictproject.exception;
import com.lecspace.ictproject.dto.ResponseDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>(false, errors, "Validation error");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO<Map<String, String>>> handleCustomExceptions(CustomException ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        if (ex.getErrorCode() != null) {
            errorDetails.put("errorCode", ex.getErrorCode());
        }
        if (ex.getDetails() != null) {
            errorDetails.put("details", ex.getDetails());
        }
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>(false, errorDetails, "Custom error occurred");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseDTO<String>> handleAllExceptions(Exception ex, WebRequest request) {
//        ResponseDTO<String> response = new ResponseDTO<>(false, null, "An unexpected error occurred");
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseDTO<Void>> handleAuthenticationException(AuthenticationException ex) {
        ResponseDTO<Void> response = new ResponseDTO<>(false, null, "Authentication failed: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseDTO<Void>> handleJwtException(JwtException ex) {
        ResponseDTO<Void> response = new ResponseDTO<>(false, null, "Invalid or expired token: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseDTO<String>> handleAllExceptions(Exception ex, WebRequest request)
//    {
//        ex.printStackTrace();  // Log the stack trace to the console
//        ResponseDTO<String> response = new ResponseDTO<>(false, null, "An unexpected error occurred");
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("An unexpected error occurred", ex);  // Log the exception using SLF4J
        ResponseDTO<String> response = new ResponseDTO<>(false, null, "An unexpected error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}