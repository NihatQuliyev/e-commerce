package delivery.management.system.model.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private long count;
    private BigDecimal price;
    private List<Path> imagesPath;
    private List<CategoryResponseDto> categories;
}
