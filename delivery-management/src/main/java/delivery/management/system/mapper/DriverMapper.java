package delivery.management.system.mapper;

import delivery.management.system.model.dto.request.DriverRequestDto;
import delivery.management.system.model.dto.response.DriverResponseDto;
import delivery.management.system.model.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverResponseDto map(Driver driver);

    Driver map(DriverRequestDto driverRequest);


    Driver map(@MappingTarget Driver beforeDriver, DriverRequestDto driverRequest);
}
