package cz.jpetrla.cleevio.watchapp.service;

import cz.jpetrla.cleevio.watchapp.mapper.WatchServiceMapper;
import cz.jpetrla.cleevio.watchapp.model.Watch;
import cz.jpetrla.cleevio.watchapp.repository.WatchJpaRepository;
import cz.jpetrla.cleevio.watchapp.repository.entity.WatchEntity;
import cz.jpetrla.cleevio.watchapp.service.impl.WatchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WatchServiceImplTest {

    @Mock
    private WatchJpaRepository repository;

    @Mock
    private WatchServiceMapper mapper;

    @InjectMocks
    private WatchServiceImpl service;

    @Test
    void testUpload() {
        Watch watch = new Watch();
        watch.setTitle("Watch fountaint");
        watch.setPrice(2500);
        watch.setDescription("Beautiful watch with fountain");
        watch.setFountain("Base64 image".getBytes());

        WatchEntity watchEntity = new WatchEntity();

        when(mapper.toWatchEntity(watch)).thenReturn(watchEntity);

        service.upload(watch);

        verify(mapper).toWatchEntity(watch);
        verify(repository).save(watchEntity);
    }
}
