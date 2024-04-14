package com.onebox.backend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description Exception handler that intercepts the requests in case that any
 *              of the below exceptions are raised.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the IllegalArgumentException to return a simpler message.
     * 
     * @param ex the exception raised.
     * @return the object with the error message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the NullPointerException to return a simpler message.
     * 
     * @param ex the exception raised
     * @return the object with the error message.
     */
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNullArgument(NullPointerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
