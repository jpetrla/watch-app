package cz.jpetrla.cleevio.watchapp.service;

import cz.jpetrla.cleevio.watchapp.model.Watch;

/**
 * Watch service interface.
 *
 * @author Jan Petrla
 */
public interface WatchService {

    /**
     * Upload watch operation
     *
     * @param watch {@link Watch}
     */
    void upload(Watch watch);
}
