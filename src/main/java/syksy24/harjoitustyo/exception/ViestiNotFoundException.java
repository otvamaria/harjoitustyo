package syksy24.harjoitustyo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ViestiNotFoundException extends RuntimeException {
    public ViestiNotFoundException(String message) {
        super(message);
    }
}
