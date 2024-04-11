package delivery.management.system.model.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DashboardResponseDto {

    private int customerCount;
    private int driverCount;
    private BigDecimal dailyProfit;
    private BigDecimal monthlyProfit;
    private BigDecimal yearlyProfit;
    private long dailyOrderCount;
    private long monthlyOrderCount;
    private long yearlyOrderCount;

}
