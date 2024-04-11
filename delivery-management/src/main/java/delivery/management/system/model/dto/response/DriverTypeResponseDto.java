package delivery.management.system.model.dto.response;

import delivery.management.system.model.enums.DriverStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverTypeResponseDto {

    private long id;
    private DriverStatus status;

}
