package delivery.management.system.model.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDashboardDto {

    private BigDecimal sum;
    private int count;
}
