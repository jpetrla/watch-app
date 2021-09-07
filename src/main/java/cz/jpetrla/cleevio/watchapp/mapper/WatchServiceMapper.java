package cz.jpetrla.cleevio.watchapp.mapper;

import cz.jpetrla.cleevio.watchapp.config.MapStructGlobalConfig;
import cz.jpetrla.cleevio.watchapp.model.Watch;
import cz.jpetrla.cleevio.watchapp.repository.entity.WatchEntity;
import cz.jpetrla.cleevio.watchapp.service.WatchService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for {@link WatchService}.
 *
 * @author Jan Petrla
 */
@Mapper(config = MapStructGlobalConfig.class)
public interface WatchServiceMapper {

    /**
     * Mapper for {@link Watch}
     *
     * @param watch {@link Watch}
     * @return {@link WatchEntity}
     */
    @Mapping(target = "id", ignore = true)
    WatchEntity toWatchEntity(Watch watch);
}
