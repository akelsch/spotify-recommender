package de.htwsaar.spotifyrecommender.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.server.ResponseStatusException;

/**
 * {@link ResponseStatusException} subclass that mimics {@link MissingServletRequestParameterException}.
 */
public class MissingRequestParameterException extends ResponseStatusException {

    private final String parameterName;

    public MissingRequestParameterException(String parameterName) {
        super(HttpStatus.BAD_REQUEST);
        this.parameterName = parameterName;
    }

    @Override
    public String getReason() {
        return "Required parameter '%s' is not present".formatted(parameterName);
    }
}
