package de.htwsaar.spotifyrecommender.util;

import de.htwsaar.spotifyrecommender.util.exception.EnumMismatchException;
import de.htwsaar.spotifyrecommender.util.exception.MissingRequestParameterException;
import de.htwsaar.spotifyrecommender.util.exception.NumberMismatchException;
import org.springframework.util.NumberUtils;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Optional;

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

    public static <T extends Enum<T>> T requiredEnumQueryParam(ServerRequest request, String paramName, Class<T> clazz) {
        return RequestUtils.enumQueryParam(request, paramName, clazz)
                .orElseThrow(() -> new MissingRequestParameterException(paramName));
    }

    public static <T extends Enum<T>> Optional<T> enumQueryParam(ServerRequest request, String paramName, Class<T> clazz) {
        Optional<String> paramOptional = request.queryParam(paramName);

        if (paramOptional.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(Enum.valueOf(clazz, paramOptional.get()));
        } catch (IllegalArgumentException e) {
            throw new EnumMismatchException(paramName, clazz, e);
        }
    }
}
