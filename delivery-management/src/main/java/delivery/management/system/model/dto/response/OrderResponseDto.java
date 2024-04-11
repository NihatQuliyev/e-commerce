package delivery.management.system.model.dto.response;


import delivery.management.system.model.entity.Cart;
import delivery.management.system.model.entity.Driver;
import delivery.management.system.model.entity.OrderStatus;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private long id;
    private String place;
    private BigDecimal totalAmount;
    private Cart cart;
    private List<OrderItemResponseDto> orderItems;
    private DriverResponseDto driver;
    private OrderStatusResponseDto orderStatus;

    private LocalDateTime deliveryTime;
}
