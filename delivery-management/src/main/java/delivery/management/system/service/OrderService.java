package delivery.management.system.service;

import delivery.management.system.model.dto.request.PaymentRequestDto;
import delivery.management.system.model.dto.response.OrderDashboardDto;
import delivery.management.system.model.dto.response.OrderResponseDto;
import delivery.management.system.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderDashboardDto profitByDate(LocalDate localDate);

    ResponseEntity<Void> order(User user, PaymentRequestDto paymentRequest);

    ResponseEntity<List<OrderResponseDto>> findALL();

    ResponseEntity<OrderResponseDto> findById(long id);

    ResponseEntity<List<OrderResponseDto>> findAllCustomerOrder();

    ResponseEntity<OrderResponseDto> findOrder();

    void orderDelivered();
}
