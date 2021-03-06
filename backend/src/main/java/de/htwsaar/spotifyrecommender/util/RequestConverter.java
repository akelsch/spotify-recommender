package de.htwsaar.spotifyrecommender.util;

import de.htwsaar.spotifyrecommender.util.exception.EnumMismatchException;
import de.htwsaar.spotifyrecommender.util.exception.MissingRequestParameterException;
import de.htwsaar.spotifyrecommender.util.exception.NumberMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Optional;

@Component
public class RequestConverter {

    private final ConversionService conversionService;

    @Autowired
    public RequestConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public <T> T requiredQueryParam(ServerRequest request, String paramName, Class<T> targetType) {
        return queryParam(request, paramName, targetType)
                .orElseThrow(() -> new MissingRequestParameterException(paramName));
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> queryParam(ServerRequest request, String paramName, Class<T> targetType) {
        String paramValue = request.queryParam(paramName).orElse(null);

        try {
            return Optional.ofNullable(conversionService.convert(paramValue, targetType));
        } catch (ConversionException e) {
            if (targetType.isEnum()) {
                throw new EnumMismatchException(paramName, (Class<? extends Enum<?>>) targetType, e);
            } else {
                throw e;
            }
        }
    }

    public <T> T pathVariable(ServerRequest request, String variableName, Class<T> targetType) {
        String variableValue = request.pathVariable(variableName);

        try {
            return conversionService.convert(variableValue, targetType);
        } catch (ConversionException e) {
            if (Number.class.isAssignableFrom(targetType)) {
                throw new NumberMismatchException(variableValue, e);
            } else {
                throw e;
            }
        }
    }
}
