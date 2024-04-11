package delivery.management.system.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRequestDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;
    private List<Long> permissions;
}
