package de.htwsaar.spotifyrecommender.util.web;

import de.htwsaar.spotifyrecommender.util.web.exception.EnumMismatchException;
import de.htwsaar.spotifyrecommender.util.web.exception.MissingRequestParameterException;
import de.htwsaar.spotifyrecommender.util.web.exception.NumberMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Optional;

/**
 * Utility that unifies reading and converting String values in a {@link ServerRequest},
 * e.g. query parameters and path variables, to arbitrary objects.
 *
 * @see ConversionService
 */
@Component
public class RequestConverter {

    private final ConversionService conversionService;

    @Autowired
    public RequestConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * Gets a query parameter of given name and type.
     *
     * @param request    the given request
     * @param paramName  the name of the query parameter
     * @param targetType the expected query parameter type
     * @param <T>        the target type
     * @return the query parameter converted to the given type
     * @throws MissingRequestParameterException in case the query parameter could not be found
     */
    public <T> T requiredQueryParam(ServerRequest request, String paramName, Class<T> targetType) {
        return queryParam(request, paramName, targetType)
                .orElseThrow(() -> new MissingRequestParameterException(paramName));
    }

    /**
     * Gets a query parameter of given name and type.
     *
     * @param request    the given request
     * @param paramName  the name of the query parameter
     * @param targetType the expected query parameter type
     * @param <T>        the target type
     * @return the query parameter converted to the given type
     * @throws EnumMismatchException in case of a query parameter of type {@link Enum}
     *                               that could not be matched to one of its values
     */
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

    /**
     * Gets a path variable of given name and type.
     *
     * @param request      the given request
     * @param variableName the name of the path variable
     * @param targetType   the expected path variable type
     * @param <T>          the target type
     * @return the path variable converted to the given type
     * @throws NumberMismatchException in case of a path variable of type {@link Number}
     *                                 that could not be converted to a valid number
     */
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
