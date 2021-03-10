package de.htwsaar.spotifyrecommender.util.web.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * {@link ResponseStatusException} subclass that mimics {@link TypeMismatchException} for {@link Number} classes.
 */
public class NumberMismatchException extends ResponseStatusException {

    private final String value;

    public NumberMismatchException(String value, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, null, cause);
        this.value = value;
    }

    @Override
    public String getReason() {
        return "Cannot convert '%s' to a number".formatted(value);
    }
}
