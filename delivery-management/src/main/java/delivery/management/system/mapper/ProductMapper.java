package delivery.management.system.mapper;

import delivery.management.system.model.dto.request.ProductRequestDto;
import delivery.management.system.model.dto.request.ProductUpdateRequestDto;
import delivery.management.system.model.dto.response.ProductResponseDto;
import delivery.management.system.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "imagesPath",ignore = true)
    ProductResponseDto map(Product product);

    @Mapping(target = "categories",ignore = true)
    Product map(ProductRequestDto productRequest);

    Product map(@MappingTarget Product product, ProductUpdateRequestDto productRequestDto);
}
