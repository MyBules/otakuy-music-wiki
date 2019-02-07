package com.otakuy.otakuymusic.handler;

import com.otakuy.otakuymusic.exception.AuthorityException;
import com.otakuy.otakuymusic.exception.CheckException;
import com.otakuy.otakuymusic.exception.UnsupportedFormatException;
import com.otakuy.otakuymusic.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class OtakuyExceptionHandler {
    @ExceptionHandler(CheckException.class)
    public ResponseEntity handleCheckException(CheckException cex) {
        return ResponseEntity.status(cex.getResult().getHttpStatus()).body(cex.getResult());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity handleWebExchangeBindException(WebExchangeBindException webe) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>(webe.getFieldErrors().stream()
                .map(e -> e.getField() + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + s2)));
    }

    @ExceptionHandler(UnsupportedFormatException.class)
    public ResponseEntity handleUnsupportedFormatException(UnsupportedFormatException ufe) {
        return ResponseEntity.status(ufe.getResult().getHttpStatus()).body(ufe.getResult());
    }

    @ExceptionHandler(AuthorityException.class)
    public ResponseEntity handleAuthorityException(AuthorityException ae) {
        return ResponseEntity.status(ae.getResult().getHttpStatus()).body(ae.getResult());
    }
}
