package delivery.management.system.model.dto.response;

import delivery.management.system.model.entity.Product;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {

    private long id;
    private int count;
    private BigDecimal totalAmount;
    private ProductResponseDto product;
}
