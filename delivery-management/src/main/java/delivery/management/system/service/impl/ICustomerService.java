package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.mapper.UserMapper;
import delivery.management.system.model.dto.request.PaymentRequestDto;
import delivery.management.system.model.dto.response.CartResponseDto;
import delivery.management.system.model.dto.response.UserResponseDto;
import delivery.management.system.model.entity.User;
import delivery.management.system.model.enums.RoleType;
import delivery.management.system.repository.UserRepository;
import delivery.management.system.service.AuthService;
import delivery.management.system.service.CartService;
import delivery.management.system.service.CustomerService;
import delivery.management.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ICustomerService implements CustomerService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExceptionService exceptionService;
    private final AuthService authService;
    private final CartService cartService;
    private final OrderService orderService;

    @Override
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userRepository.findUsersByRole_Name(RoleType.CUSTOMER.name()).stream()
                .map(userMapper::map)
                .toList());
    }

    @Override
    public ResponseEntity<UserResponseDto> findById(long id) {
        return ResponseEntity.ok(userMapper.map(getById(id)));
    }

    @Override
    public ResponseEntity<UserResponseDto> findById() {
        return ResponseEntity.ok(userMapper.map(authService.getAuthenticatedUser()));
    }

    @Override
    public ResponseEntity<Void> delete(long id) {
        User customer = getById(id);
        customer.setEnable(false);
        userRepository.save(customer);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> addProduct(long productId, int count) {
        User user = authService.getAuthenticatedUser();
        cartService.addProductToCart(productId, user, count);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CartResponseDto> getProductToCart() {
        User user = authService.getAuthenticatedUser();
        return cartService.getProductToCart(user);
    }

    @Override
    public ResponseEntity<Void> deleteProductToCart(long productId) {
        User user = authService.getAuthenticatedUser();
        return cartService.deleteProductToCart(user,productId);
    }

    @Override
    public ResponseEntity<Void> buyProductFromCart(PaymentRequestDto paymentRequest) {
        User user = authService.getAuthenticatedUser();
        return orderService.order(user, paymentRequest);
    }

    private User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("exception.driver.not.found", HttpStatus.NOT_FOUND)));
    }



}
