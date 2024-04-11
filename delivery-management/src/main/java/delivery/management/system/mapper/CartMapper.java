package delivery.management.system.mapper;

import delivery.management.system.model.dto.response.CartResponseDto;
import delivery.management.system.model.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "cartItems.product.images", ignore = true)
    CartResponseDto map(Cart userCart);
}
