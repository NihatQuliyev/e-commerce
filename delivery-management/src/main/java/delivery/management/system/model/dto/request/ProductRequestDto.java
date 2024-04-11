package delivery.management.system.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductRequestDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @Positive(message = "Price must be positive")
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @Positive(message = "Count must be positive")
    @NotNull(message = "Count cannot be null")
    private Long count;

    @NotNull(message = "Categories ID cannot be null")
    private List<Long> categoriesId;

}

