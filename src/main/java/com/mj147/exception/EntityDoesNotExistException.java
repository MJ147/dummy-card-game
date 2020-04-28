package com.mj147.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ResponseBody
public class EntityDoesNotExistException extends RuntimeException {
    public EntityDoesNotExistException() {
        super();
    }

    public EntityDoesNotExistException(String message) {
        super(message);
    }
}
