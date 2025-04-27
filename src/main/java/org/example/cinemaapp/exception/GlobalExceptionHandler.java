package org.example.cinemaapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.NOT_FOUND.value());
    errorBody.put("error", "Not Found");
    errorBody.put("message", ex.getMessage());
    errorBody.put("path", "");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.BAD_REQUEST.value());
    errorBody.put("error", "Validation Error");

    Map<String, String> fieldErrors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage())
    );

    errorBody.put("messages", fieldErrors);
    errorBody.put("path", "");

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
  }
}