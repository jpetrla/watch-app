package cz.jpetrla.cleevio.watchapp.service;

import cz.jpetrla.cleevio.watchapp.model.Watch;
import cz.jpetrla.cleevio.watchapp.repository.WatchJpaRepository;
import cz.jpetrla.cleevio.watchapp.repository.entity.WatchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class WatchServiceImplTest {

    @Autowired
    private WatchJpaRepository repository;

    @Autowired
    private WatchService service;

    @Test
    void testUpload() {
        Watch watch = new Watch();
        watch.setTitle("Watch fountaint");
        watch.setPrice(2500);
        watch.setDescription("Beautiful watch with fountain");
        watch.setFountain("Base64 image".getBytes());

        List<WatchEntity> watchEntities = repository.findAll();
        assertEquals(0, watchEntities.size());

        service.upload(watch);

        watchEntities = repository.findAll();
        assertEquals(1, watchEntities.size());

        WatchEntity foundWatchEntity = watchEntities.get(0);
        assertEquals(watch.getTitle(), foundWatchEntity.getTitle());
        assertEquals(watch.getPrice(), foundWatchEntity.getPrice());
        assertEquals(watch.getDescription(), foundWatchEntity.getDescription());
        assertEquals(watch.getFountain().length, foundWatchEntity.getFountain().length);

        repository.deleteAll();
    }
}
