package delivery.management.system.controller;

import delivery.management.system.model.dto.request.DriverRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.dto.response.DriverResponseDto;
import delivery.management.system.model.dto.response.DriverTypeResponseDto;
import delivery.management.system.model.dto.response.OrderResponseDto;
import delivery.management.system.service.DriverService;
import delivery.management.system.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("drivers/")
@RequiredArgsConstructor
@Slf4j
public class DriverController {

    private final DriverService driverService;
    private final OrderService orderService;

    @PostMapping
    ResponseEntity<Void> registration(@RequestBody @Valid DriverRequestDto driverRequest) {
        return driverService.registration(driverRequest);
    }

//    @GetMapping("driver-types")
//    ResponseEntity<List<DriverTypeResponseDto>> getAllDriverType() {
//        return driverService.getAllDriverType();
//    }

    @GetMapping("find-by-id") //FIXME JWT INITIAL
    ResponseEntity<DriverResponseDto> findById() {
        return driverService.findById();
    }

    @PutMapping("updated")
    ResponseEntity<Void> update(@RequestBody @Valid DriverRequestDto driverRequestDto) {
        log.error("{}",driverRequestDto);
        return driverService.update(driverRequestDto);
    }

    @GetMapping("order")
    ResponseEntity<OrderResponseDto> order() {
        return orderService.findOrder();
    }

    @PatchMapping("order/delivered")
    ResponseEntity<Void> orderDelivered() {
        return driverService.orderDelivered();
    }

}
