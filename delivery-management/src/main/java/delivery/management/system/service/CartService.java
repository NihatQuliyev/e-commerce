package delivery.management.system.service;

import delivery.management.system.model.dto.response.CartResponseDto;
import delivery.management.system.model.entity.Cart;
import delivery.management.system.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface CartService {
    void addProductToCart(long productId, User user, int count);

    ResponseEntity<CartResponseDto> getProductToCart(User user);

    ResponseEntity<Void> deleteProductToCart(User user, long productId);

    Cart getCartByUser(User user);

    void saveCart(User user);
}
