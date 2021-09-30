package com.lyx;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*指定找不到的状态*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFountException extends RuntimeException{
    public NotFountException() {
    }

    public NotFountException(String message) {
        super(message);
    }

    public NotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}
