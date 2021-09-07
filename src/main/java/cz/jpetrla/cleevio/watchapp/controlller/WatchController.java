package cz.jpetrla.cleevio.watchapp.controlller;

import cz.jpetrla.cleevio.watchapp.model.Watch;
import cz.jpetrla.cleevio.watchapp.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Watch controller for processing HTTP requests.
 *
 * @author Jan Petrla
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class WatchController {

    private final WatchService watchService;

    /**
     * Upload watch item via POST http request
     *
     * @param watch {@link Watch}
     * @return {@link ResponseEntity}
     */
    @PostMapping(value = "/watch", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> upload(@RequestBody @Valid final Watch watch) {
        watchService.upload(watch);
        return createResponseEntity("Upload successful");
    }

    private ResponseEntity<Object> createResponseEntity(final String message) {
        final HttpStatus status = HttpStatus.CREATED;

        final Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put(("message"), message);

        return new ResponseEntity<>(body, status);
    }
}
