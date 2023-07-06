package com.example.booking.exception;

public class BookingException extends Exception {
    public BookingException(String message){
        super(message);
    }

    public BookingException(){
        super();
    }
    public static class DuplicateFieldException extends BookingException {
        public DuplicateFieldException(String message) {super(message);}
    }

    public static class EmptyFieldException extends BookingException {
        public EmptyFieldException(String message) {super(message);}
    }

    public static class NotFoundException extends BookingException {
        public NotFoundException(String message) {super(message);}
    }

    public static class IllegalException extends BookingException {
        public IllegalException(String message) {super(message);}
    }
    public static class TokenRefreshException extends BookingException {
        public TokenRefreshException(String message){super(message);}
    }
    public static class TokenExpireException extends BookingException {
        public TokenExpireException(String message){super(message);}
    }
}
