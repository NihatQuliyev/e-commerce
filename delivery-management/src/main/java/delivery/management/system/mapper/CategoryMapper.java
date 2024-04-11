package delivery.management.system.mapper;

import delivery.management.system.model.dto.request.CategoryRequestDto;
import delivery.management.system.model.dto.response.CategoryResponseDto;
import delivery.management.system.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryResponseDto map(Category category);

    Category map(CategoryRequestDto categoryRequestDto);


    Category map(@MappingTarget Category category, CategoryRequestDto categoryRequestDto);
}
