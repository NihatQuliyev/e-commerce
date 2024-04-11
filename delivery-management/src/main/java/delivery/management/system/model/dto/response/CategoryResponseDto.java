package delivery.management.system.model.dto.response;

import delivery.management.system.model.dto.request.ProductRequestDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    private long id;
    private String name;
    private String description;

}
