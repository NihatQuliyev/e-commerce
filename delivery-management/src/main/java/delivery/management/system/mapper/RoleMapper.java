package delivery.management.system.mapper;

import delivery.management.system.model.dto.request.RoleRequestDto;
import delivery.management.system.model.dto.response.RoleResponseDto;
import delivery.management.system.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    RoleResponseDto map(Role role);

    @Mapping(target = "permissions",ignore = true)
    @Mapping(target = "id",ignore = true)
    Role map(RoleRequestDto roleRequestDto);
}
