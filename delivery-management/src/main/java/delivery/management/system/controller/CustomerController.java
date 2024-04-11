package delivery.management.system.controller;

import delivery.management.system.model.dto.request.PaymentRequestDto;
import delivery.management.system.model.dto.response.CartResponseDto;
import delivery.management.system.model.dto.response.OrderResponseDto;
import delivery.management.system.model.dto.response.UserResponseDto;
import delivery.management.system.service.CustomerService;
import delivery.management.system.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers/")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("find-by-id")
    ResponseEntity<UserResponseDto> findById() {
        return customerService.findById();
    }

    @PostMapping("{product-id}/cart")
    ResponseEntity<Void> addProductToCart(@PathVariable(name = "product-id") long productId,
                                          @RequestParam(required = false) int count) {
        return customerService.addProduct(productId, count);
    }

    @GetMapping("cart")
    ResponseEntity<CartResponseDto> getProductToCart() {
        return customerService.getProductToCart();
    }

    @DeleteMapping("cart/{productId}")
    ResponseEntity<Void> deleteProductToCart(@PathVariable long productId) {
        return customerService.deleteProductToCart(productId);
    }

    @PostMapping("cart/checkout")
    ResponseEntity<Void> buyProductsFromCart(@RequestBody @Valid PaymentRequestDto paymentRequest) {
        return customerService.buyProductFromCart(paymentRequest);
    }

    @GetMapping("orders")
    ResponseEntity<List<OrderResponseDto>> findByOrderById() {
        return orderService.findAllCustomerOrder();
    }
}
