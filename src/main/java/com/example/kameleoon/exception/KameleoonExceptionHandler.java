package com.example.kameleoon.exception;

import com.example.kameleoon.dto.MessageExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class KameleoonExceptionHandler {
    @ExceptionHandler(KameleoonException.class)
    public ResponseEntity<?> errorHandler(KameleoonException ex) {
        String msg = "Error";
        log.info(msg + ex.getMessage());
        return new ResponseEntity<>(new MessageExceptionDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
