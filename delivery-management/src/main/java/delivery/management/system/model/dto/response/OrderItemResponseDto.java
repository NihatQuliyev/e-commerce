package delivery.management.system.model.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {

    private long id;
    private int count;
    private BigDecimal total_amount;
    private ProductResponseDto product;
}
