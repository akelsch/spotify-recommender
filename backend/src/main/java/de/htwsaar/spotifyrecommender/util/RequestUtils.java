package de.htwsaar.spotifyrecommender.util;

import de.htwsaar.spotifyrecommender.util.exception.EnumMismatchException;
import de.htwsaar.spotifyrecommender.util.exception.MissingRequestParameterException;
import de.htwsaar.spotifyrecommender.util.exception.NumberMismatchException;
import org.springframework.util.NumberUtils;
import org.springframework.web.reactive.function.server.ServerRequest;

public final class RequestUtils {

    private RequestUtils() {
    }

    public static Long parseLong(String text) {
        return RequestUtils.parseNumber(text, Long.class);
    }

    public static <T extends Number> T parseNumber(String text, Class<T> clazz) {
        try {
            return NumberUtils.parseNumber(text, clazz);
        } catch (NumberFormatException e) {
            throw new NumberMismatchException(text, e);
        }
    }

    public static <T extends Enum<T>> T getEnumQueryParam(ServerRequest request, String paramName, Class<T> clazz) {
        String paramValue = request.queryParam(paramName).orElseThrow(() -> new MissingRequestParameterException(paramName));

        try {
            return Enum.valueOf(clazz, paramValue);
        } catch (IllegalArgumentException e) {
            throw new EnumMismatchException(paramName, clazz, e);
        }
    }
}
