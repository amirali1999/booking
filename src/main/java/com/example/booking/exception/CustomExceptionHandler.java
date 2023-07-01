package com.example.booking.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<?> defaultHandler(Exception exception){
        ExceptionType exceptionType = ExceptionType.fromException(exception);
        String message = exception.getMessage()+exceptionType.getMessage();
        return new Response(exceptionType.getStatus(),
                message,
                null,
                0
        ).createResponseEntity();
    }
}
