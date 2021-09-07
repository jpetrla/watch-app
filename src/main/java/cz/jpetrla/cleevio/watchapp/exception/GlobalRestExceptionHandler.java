package cz.jpetrla.cleevio.watchapp.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Global class for handling exceptions.
 *
 * @author Jan Petrla
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final List<String> errors = Collections.singletonList(ex.getMessage());

        return createResponseEntity(errors, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        return createResponseEntity(errors, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        String message;

        final Throwable t = ex.getRootCause();
        if (t instanceof InvalidFormatException) {
            final InvalidFormatException ife = (InvalidFormatException) t;
            final List<JsonMappingException.Reference> path = ife.getPath();

            final String fieldName = path.get(0).getFieldName();
            final String originalMessage = ife.getOriginalMessage();

            message = fieldName + ": " + originalMessage;
        } else {
            message = ex.getMessage();
        }

        final List<String> errors = Collections.singletonList(message);

        return createResponseEntity(errors, headers, status);
    }

    private ResponseEntity<Object> createResponseEntity(final List<String> errors, final HttpHeaders headers, final HttpStatus status) {
        final Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }
}
