package org.fss.exception;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException ex,
      WebRequest request) {
    ExceptionMessage message = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), LocalDate.now(),
        ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionMessage> globalHandler(Exception ex, WebRequest request) {
    ExceptionMessage message = new ExceptionMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        LocalDate.now(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<ExceptionMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
