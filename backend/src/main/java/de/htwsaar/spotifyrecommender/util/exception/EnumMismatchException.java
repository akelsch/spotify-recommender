package de.htwsaar.spotifyrecommender.util.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

/**
 * {@link ResponseStatusException} subclass that mimics {@link TypeMismatchException} for {@link Enum} classes.
 */
public class EnumMismatchException extends ResponseStatusException {

    private final String parameterName;
    private final Class<? extends Enum<?>> requiredType;

    public EnumMismatchException(String parameterName, Class<? extends Enum<?>> requiredType, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, null, cause);
        this.parameterName = parameterName;
        this.requiredType = requiredType;
    }

    @Override
    public String getReason() {
        String enumConstants = Arrays.toString(requiredType.getEnumConstants());
        return "Parameter '%s' must be one of %s".formatted(parameterName, enumConstants);
    }
}
