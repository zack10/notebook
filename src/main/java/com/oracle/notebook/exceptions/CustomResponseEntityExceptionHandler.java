package com.oracle.notebook.exceptions;

import com.oracle.notebook.controller.dto.ExceptionMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

    @ExceptionHandler(UnknownInterpreterException.class)
    public final ResponseEntity<Object> handleUnknownInterpreterException(UnknownInterpreterException ex, WebRequest req){
        ExceptionMessageDto exceptionResponse = new ExceptionMessageDto(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PythonSyntaxException.class)
    public final ResponseEntity<Object> handlePythonSyntaxException(PythonSyntaxException ex, WebRequest req) {
        ExceptionMessageDto exceptionResponse = new ExceptionMessageDto(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedPyInterpreterException.class)
    public final ResponseEntity<Object> handleUnexpectedPyInterpreterException(UnexpectedPyInterpreterException ex, WebRequest req) {
        ExceptionMessageDto exceptionResponse = new ExceptionMessageDto(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
