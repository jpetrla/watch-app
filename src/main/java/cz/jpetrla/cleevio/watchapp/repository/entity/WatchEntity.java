package cz.jpetrla.cleevio.watchapp.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * Watch entity.
 *
 * @author Jan Petrla
 */
@Data
@Entity
@Table(name = "watch")
public class WatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @Column(name = "fountain")
    private byte[] fountain;
}
