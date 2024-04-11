package delivery.management.system.mapper;

import delivery.management.system.model.dto.response.DriverTypeResponseDto;
import delivery.management.system.model.entity.DriverType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverTypeMapper {

    DriverTypeResponseDto map(DriverType  driverType);
}
