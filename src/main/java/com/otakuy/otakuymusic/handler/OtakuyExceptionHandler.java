package com.otakuy.otakuymusic.handler;

import com.otakuy.otakuymusic.exception.CheckException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OtakuyExceptionHandler {
    @ExceptionHandler(CheckException.class)
    public ResponseEntity handleCheckException(CheckException cex) {
        return ResponseEntity.status(cex.getResult().getHttpStatus()).body(cex.getResult());
    }
}
