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
public class ProductUpdateRequestDto {

    private String name;
    private String description;
    private BigDecimal price;
    private Long count;
    private List<Long> categoriesId;
}
