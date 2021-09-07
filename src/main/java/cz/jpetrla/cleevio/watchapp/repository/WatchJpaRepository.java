package cz.jpetrla.cleevio.watchapp.repository;

import cz.jpetrla.cleevio.watchapp.repository.entity.WatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Watch JPA repository.
 *
 * @author Jan Petrla
 */
@Repository
public interface WatchJpaRepository extends JpaRepository<WatchEntity, Long> {
}
