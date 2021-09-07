package cz.jpetrla.cleevio.watchapp.service.impl;

import cz.jpetrla.cleevio.watchapp.mapper.WatchServiceMapper;
import cz.jpetrla.cleevio.watchapp.model.Watch;
import cz.jpetrla.cleevio.watchapp.repository.WatchJpaRepository;
import cz.jpetrla.cleevio.watchapp.repository.entity.WatchEntity;
import cz.jpetrla.cleevio.watchapp.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link WatchService}.
 *
 * @author Jan Petrla
 */
@RequiredArgsConstructor
@Service
public class WatchServiceImpl implements WatchService {

    private final WatchJpaRepository watchRepository;
    private final WatchServiceMapper mapper;

    @Override
    public void upload(final Watch watch) {
        final WatchEntity watchEntity = mapper.toWatchEntity(watch);
        watchRepository.save(watchEntity);
    }
}
