package cz.jpetrla.cleevio.watchapp.model;

import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
