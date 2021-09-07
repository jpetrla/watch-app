package cz.jpetrla.cleevio.watchapp.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Watch POJO.
 *
 * @author Jan Petrla
 */
@Data
public class Watch {

    @NotBlank
    private String title;

    @NotNull
    @Positive
    private Integer price;

    @NotBlank
    private String description;

    @NotEmpty
    @ToString.Exclude
    private byte[] fountain;
}
