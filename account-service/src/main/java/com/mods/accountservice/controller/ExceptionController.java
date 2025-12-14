package com.mods.accountservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> conflictData(Exception ex) {
        log.error("Exception occurred: {}", ex.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("code", "409");
        map.put("error", "Conflict: Data integrity violation.");
        return map;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, String> methodNotAllowed(Exception ex) {
        log.error("Exception occurred: {}", ex.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("code", "405");
        map.put("error", "Method Not Allowed");
        return map;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> badRequest(Exception ex) {
        log.error("Exception occurred: {}", ex.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("code", "405");
        map.put("error", "Method Not Allowed");
        return map;
    }
}
