package cz.jpetrla.cleevio.watchapp.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global class for handling exceptions.
 *
 * @author Jan Petrla
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            final HttpMediaTypeNotSupportedException ex,
            @NonNull final HttpHeaders headers,
            @NonNull final HttpStatusCode statusCode,
            @NonNull final WebRequest request) {
        final List<String> errors = Collections.singletonList(ex.getMessage());

        return createResponseEntity(errors, headers, statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            @NonNull final HttpHeaders headers,
            @NonNull final HttpStatusCode statusCode,
            @NonNull final WebRequest request) {
        final List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        return createResponseEntity(errors, headers, statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            final HttpMessageNotReadableException ex,
            @NonNull final HttpHeaders headers,
            @NonNull final HttpStatusCode statusCode,
            @NonNull final WebRequest request) {
        String message;

        final Throwable t = ex.getRootCause();
        if (t instanceof InvalidFormatException ife) {
            final List<JsonMappingException.Reference> path = ife.getPath();

            final String fieldName = path.getFirst().getFieldName();
            final String originalMessage = ife.getOriginalMessage();

            message = fieldName + ": " + originalMessage;
        } else {
            message = ex.getMessage();
        }

        final List<String> errors = Collections.singletonList(message);

        return createResponseEntity(errors, headers, statusCode);
    }

    private ResponseEntity<Object> createResponseEntity(final List<String> errors, final HttpHeaders headers, final HttpStatusCode statusCode) {
        final Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("statusCode", statusCode.value());
        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, statusCode);
    }
}
