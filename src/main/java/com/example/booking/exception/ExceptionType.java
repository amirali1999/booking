package com.example.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ExceptionType {
    FIELD_IS_DUPLICATE(BookingException.DuplicateFieldException.class,
            HttpStatus.BAD_REQUEST,
            LogLevel.INFO ,
            "'s field is duplicate"
    ),
    FIELD_IS_EMPTY(BookingException.EmptyFieldException.class,
            HttpStatus.BAD_REQUEST,
            LogLevel.INFO,
            "'s field is duplicate"
    ),
    NOT_FOUND(BookingException.NotFoundException.class,
            HttpStatus.NOT_FOUND,
            LogLevel.INFO,
            "'s value not found"
    ),
    ILLEGAL(BookingException.IllegalException.class,
            HttpStatus.BAD_REQUEST,
            LogLevel.INFO,
            ""
    ),
    DEFAULT_EXCEPTION(null,
            HttpStatus.BAD_REQUEST,
            LogLevel.INFO,
            "Error not defined"
    );

    private final Class<? extends Exception> aClass;
    private final HttpStatus status;
    private final LogLevel logLevel;
    private final String message;

    public static ExceptionType fromException(Exception exception){
        for (ExceptionType exceptionType:values()){
            if (exceptionType.getAClass().equals(exception.getClass())) {
                return exceptionType;
            }
        }
        return DEFAULT_EXCEPTION;
    }
}
