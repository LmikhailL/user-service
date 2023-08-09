package com.ates.userservice.exception.handler;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.ates.userservice.exception.BadRequestException;
import com.ates.userservice.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<String> handleGeneralEx(Exception ex) {
    log.error("Error", ex);
    return ResponseEntity.internalServerError().body("Unexpected error");
  }

  @ExceptionHandler(value = BadRequestException.class)
  public ResponseEntity<String> handleBadRqEx(BadRequestException ex) {
    log.error("Error", ex);
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = UnauthorizedException.class)
  public ResponseEntity<String> handleUnauthorizedEx(UnauthorizedException ex) {
    log.error("Error", ex);
    return ResponseEntity.status(UNAUTHORIZED).body(ex.getMessage());
  }
}
