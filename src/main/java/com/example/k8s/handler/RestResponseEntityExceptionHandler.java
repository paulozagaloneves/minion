package com.example.k8s.handler;

import java.util.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({NoSuchElementException.class})
  public ResponseEntity<Object> handleNoSuchElementException(
      Exception ex, WebRequest request) {
    return new ResponseEntity<Object>(
        ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}
