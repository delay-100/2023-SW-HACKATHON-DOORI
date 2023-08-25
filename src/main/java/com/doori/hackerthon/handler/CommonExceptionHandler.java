package com.doori.hackerthon.handler;

import com.doori.hackerthon.dto.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseModel<String> runtimeExceptionHandler(RuntimeException e) {
        log.error("exception", e);
        return new ResponseModel<>(500, "error", e.getMessage());
    }

}
