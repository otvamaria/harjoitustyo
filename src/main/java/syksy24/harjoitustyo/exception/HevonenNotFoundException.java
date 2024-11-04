package syksy24.harjoitustyo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HevonenNotFoundException extends RuntimeException {
    public HevonenNotFoundException(String message) {
        super(message);
    }
}
