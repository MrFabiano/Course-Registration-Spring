package com.spring.agular.controller;

import com.spring.agular.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicatiomnControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(RecordNotFoundException ex){
        return  ex.getMessage();
    }
}
