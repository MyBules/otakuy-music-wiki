package com.otakuy.otakuymusic.exception;

import com.otakuy.otakuymusic.model.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicException extends RuntimeException {
    private Result result;
}
