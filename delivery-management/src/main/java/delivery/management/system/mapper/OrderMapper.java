package delivery.management.system.mapper;

import delivery.management.system.model.dto.response.OrderResponseDto;
import delivery.management.system.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "orderItems.product.images", ignore = true)
    OrderResponseDto map(Order order);
}
