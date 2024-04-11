package delivery.management.system.mapper;

import delivery.management.system.model.entity.Otp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OtpMapper {

    @Mappings({@Mapping(target = "id",ignore = true)})
    Otp map(@MappingTarget Otp otp, Otp newOtp);

}
