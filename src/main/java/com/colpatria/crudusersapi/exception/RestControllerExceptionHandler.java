package com.colpatria.crudusersapi.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

  private Object isMethodArgumentNotValidException(Exception ex) {
    if (ex instanceof MethodArgumentNotValidException) {
      return ((MethodArgumentNotValidException) ex).getAllErrors().parallelStream()
          .map(ObjectError::getDefaultMessage).distinct().toArray();
    }
    return ex.getMessage();
  }

  @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentNotValidException.class,
      PropertyReferenceException.class, NoSuchElementException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object badRequest(Exception ex) {
    ex.printStackTrace();
    Object error = isMethodArgumentNotValidException(ex);
    return Map.of("message", "Client error", "error", error, "timestamp", LocalDateTime.now());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public Object methodNotAllowed(HttpRequestMethodNotSupportedException ex) {
    ex.printStackTrace();
    return Map.of("timestamp", LocalDateTime.now(), "message", "Client error", "error",
        ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Object internalServerError(Exception ex) {
    ex.printStackTrace();
    return Map.of("message", "Server error", "timestamp", LocalDateTime.now());
  }
}
