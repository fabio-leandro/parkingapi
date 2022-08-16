package com.fabio.parkingapi.controllers.errors;

import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CallExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<MsgError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        MsgError msgError = new MsgError();
        msgError.setStatus(HttpStatus.NOT_FOUND.value());
        msgError.setMessage(e.getMessage());
        msgError.setError("Object Not Found.");
        msgError.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgError);
    }

}