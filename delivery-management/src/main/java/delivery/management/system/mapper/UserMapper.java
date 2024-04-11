package delivery.management.system.mapper;


import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.dto.response.UserResponseDto;
import delivery.management.system.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "isEnable", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    User map(UserRequestDto userRequestDto);

    UserResponseDto map(User user);

    @Mappings({
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "isEnable", ignore = true)
    })
    User map(UserResponseDto user);

    User map(@MappingTarget User beforeDriver, UserRequestDto userRequest);
}
