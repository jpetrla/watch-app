package cz.jpetrla.cleevio.watchapp.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Global Mapstruct config.
 *
 * @author Jan Petrla
 */
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring"
)
public interface MapStructGlobalConfig {
}
