package cz.jpetrla.cleevio.watchapp.repository.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

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
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "fountain")
    private byte[] fountain;
}
