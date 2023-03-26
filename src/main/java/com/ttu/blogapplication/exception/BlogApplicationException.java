package com.ttu.blogapplication.exception;


import org.springframework.http.HttpStatus;

public class BlogApplicationException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public BlogApplicationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
