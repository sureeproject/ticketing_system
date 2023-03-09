package com.dxc.ticket.system.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
	
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,String>> handleMyCustomException(NotFoundException ex) {
        // send message here
       // String errorMessage = "An error occurred: " + ex.getMessage();
        Map<String,String> error = new LinkedHashMap<String,String>();
        error.put("code", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
