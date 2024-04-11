package delivery.management.system.model.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {

    private long id;
    private int count;
    private BigDecimal totalAmount;
    private List<CartItemResponseDto> cartItems;


}
