package com.github.polydome.remedy.api.controller;

import com.github.polydome.remedy.api.model.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ApiError entityNotExists(EntityNotFoundException e) {
        return new ApiError(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
}
