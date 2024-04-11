package delivery.management.system.model.dto.response;

import delivery.management.system.model.enums.OrderStatusEnums;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusResponseDto {

    private long id;
    private OrderStatusEnums name;
}
