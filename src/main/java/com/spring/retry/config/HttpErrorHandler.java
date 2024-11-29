package com.spring.retry.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HttpErrorHandler {
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Map<String, String>> ResponseEntity (HttpClientErrorException.NotFound notFound){
        return new ResponseEntity<>(errorResp(notFound.getMessage(),notFound.getStatusText()), new HttpHeaders(), HttpStatus.NOT_FOUND.value());
    }

    public Map<String,String> errorResp(String reason , String code){
        Map<String,String> resp = new HashMap<>();
        resp.put("cause",code);
        resp.put("reason",reason);

        return resp;
    }
}
