package delivery.management.system.model.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String email;
    private String password;
    private Long phoneNumber;
}
