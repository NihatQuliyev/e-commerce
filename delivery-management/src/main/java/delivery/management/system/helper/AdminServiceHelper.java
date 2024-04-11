package delivery.management.system.helper;

import delivery.management.system.model.dto.response.DashboardResponseDto;
import delivery.management.system.model.dto.response.OrderDashboardDto;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceHelper {

    public DashboardResponseDto dashboardBuild(OrderDashboardDto daily, OrderDashboardDto monthly, OrderDashboardDto yearly, int customerCount, int driverCount) {
        return DashboardResponseDto.builder()
                .customerCount(customerCount)
                .driverCount(driverCount)
                .dailyProfit(daily.getSum())
                .monthlyProfit(monthly.getSum())
                .yearlyProfit(yearly.getSum())
                .dailyOrderCount(daily.getCount())
                .monthlyOrderCount(monthly.getCount())
                .yearlyOrderCount(yearly.getCount())
                .build();
    }
}
