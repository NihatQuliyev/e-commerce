package delivery.management.system.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DriverRequestDto {

    @Valid
    private UserRequestDto user;

    @NotBlank(message = "Car number cannot be empty")
    private String carNumber;


}
