package com.ttu.blogapplication.exception;

import com.ttu.blogapplication.payload.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApplicationException.class)
    public ResponseEntity<ErrorMessage> handleBlogApplicationException(BlogApplicationException exception, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request)
    {
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)-> {
            String fieldName = ((FieldError)error).getField();
            String message = ((FieldError)error).getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(Exception exception, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception exception, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
