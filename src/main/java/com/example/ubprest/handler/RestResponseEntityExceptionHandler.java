package com.example.ubprest.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value   = { Exception.class })
    protected ResponseEntity<Object> handleException(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(123).body(ex.getMessage());
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

      /*  List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ex ->ex)
                .collect(Collectors.toList());*/

        body.put("errors", ex);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
