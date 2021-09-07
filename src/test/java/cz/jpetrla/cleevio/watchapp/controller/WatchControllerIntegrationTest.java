package cz.jpetrla.cleevio.watchapp.controller;

import cz.jpetrla.cleevio.watchapp.model.Watch;
import cz.jpetrla.cleevio.watchapp.repository.WatchJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class WatchControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private WatchJpaRepository repository;

    @Test
    void testUpload() {
        Watch watch = new Watch();
        watch.setTitle("Prim");
        watch.setPrice(25000);
        watch.setDescription("A watch with a water fountain picture");
        watch.setFountain("R0lGODlhAQABAIAAAAUEBA==".getBytes());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Watch> httpEntity = new HttpEntity<>(watch, httpHeaders);

        String url = "http://localhost:" + port + "/api/v1/watch";

        assertEquals(0, repository.findAll().size());

        ResponseEntity<Object> responseEntity = template.exchange(url, HttpMethod.POST, httpEntity, Object.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(1, repository.findAll().size());

        repository.deleteAll();
    }
}
