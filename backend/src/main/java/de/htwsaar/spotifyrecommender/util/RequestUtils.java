package de.htwsaar.spotifyrecommender.util;

import de.htwsaar.spotifyrecommender.util.exception.EnumMismatchException;
import de.htwsaar.spotifyrecommender.util.exception.MissingRequestParameterException;
import org.springframework.web.reactive.function.server.ServerRequest;

public final class RequestUtils {

    public static <T extends Enum<T>> T getEnumQueryParam(ServerRequest request, String paramName, Class<T> clazz) {
        String paramValue = request.queryParam(paramName).orElseThrow(() -> new MissingRequestParameterException(paramName));

        try {
            return Enum.valueOf(clazz, paramValue);
        } catch (IllegalArgumentException e) {
            throw new EnumMismatchException(paramName, clazz, e);
        }
    }
}
