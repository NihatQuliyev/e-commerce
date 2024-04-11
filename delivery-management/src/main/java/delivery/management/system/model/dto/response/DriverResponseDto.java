package delivery.management.system.model.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverResponseDto {

    private boolean isBusy;
    private UserResponseDto user;
}
