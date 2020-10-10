package com.preschool.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PreschoolNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PreschoolNotFoundExection.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String preschoolNotFoundHandler(PreschoolNotFoundExection ex) {
        return ex.getMessage();
    }
}
