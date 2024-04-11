package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.mapper.CartMapper;
import delivery.management.system.model.dto.response.CartResponseDto;
import delivery.management.system.model.entity.Cart;
import delivery.management.system.model.entity.CartItem;
import delivery.management.system.model.entity.Product;
import delivery.management.system.model.entity.User;
import delivery.management.system.repository.CartItemRepository;
import delivery.management.system.repository.CartRepository;
import delivery.management.system.service.CartService;
import delivery.management.system.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ICartService implements CartService {

    private final ProductService productService;
    private final CartRepository cartRepository;
    private final ExceptionService exceptionService;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public void addProductToCart(long productId, User user, int productCount) {
        log.error("{csddsfsd}");
        Product product = productService.getById(productId);
        log.error("{}",product);

        Cart cart = getCartByUser(user);
        log.error("{}",cart);

        cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findAny()
                .ifPresentOrElse(cartItem -> {

                    cartItem.setCount(cartItem.getCount() + productCount);

                    BigDecimal totalAmount = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(productCount));
                    cartItem.setTotalAmount(cartItem.getTotalAmount().add(totalAmount));
                    cart.setTotalAmount(cart.getTotalAmount().add(totalAmount));

                        }, () -> {

                    CartItem cartItem = buildCartItem(productCount, product, cart);
                    cartItemRepository.save(cartItem);
                    BigDecimal totalAmount = cart.getTotalAmount().add(cartItem.getTotalAmount());
                    cart.setTotalAmount(totalAmount);
                    cart.setCount(cart.getCount() + 1);
                        }
                );
    }

    @Override
    public ResponseEntity<CartResponseDto> getProductToCart(User user) {
        Cart userCart = cartRepository.findByUser(user)
                .orElseThrow(() -> new  ApplicationException(exceptionService.exceptionResponse("exception.not.found",HttpStatus.NOT_FOUND)));

        return ResponseEntity.ok(cartMapper.map(userCart));
    }

    @Override
    public ResponseEntity<Void> deleteProductToCart(User user, long productId) {
        Cart userCart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("exception.not.found.cart", HttpStatus.NOT_FOUND)));
        CartItem cartItem = userCart.getCartItems().stream()
                        .filter(item -> item.getProduct().getId() == productId)
                                .findAny().orElseThrow(() ->
                        new ApplicationException(exceptionService.exceptionResponse("exception.not.found", HttpStatus.NOT_FOUND)));

        userCart.setTotalAmount((userCart.getTotalAmount().subtract(cartItem.getTotalAmount())));
        userCart.setCount(userCart.getCount() - 1);
        log.error("{}", cartItem);
        log.error("{}", cartItem.getId());
        cartItemRepository.deleteById(cartItem.getId());

        return ResponseEntity.noContent().build();
    }

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseThrow(() ->
                new ApplicationException(exceptionService.exceptionResponse("exception.not.found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void saveCart(User user) {

        Cart cart = Cart.builder()
                .user(user)
                .build();
        cartRepository.save(cart);
    }


    private CartItem buildCartItem(int productCount, Product product, Cart cart) {

        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(productCount));

        return CartItem.builder()
                .count(productCount)
                .totalAmount(totalAmount)
                .product(product)
                .cart(cart)
                .build();
    }
}
