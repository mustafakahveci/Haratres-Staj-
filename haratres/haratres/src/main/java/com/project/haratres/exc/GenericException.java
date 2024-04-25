package com.project.haratres.exc;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class GenericException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public GenericException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
