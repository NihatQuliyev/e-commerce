package common.notification.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@Getter
@Setter
public class EmailRequest {
    @NotBlank()
    @Email()
    private String to;

    @NotBlank()
    @Size(max = 100)
    private String subject;

    @NotBlank()
    private String text;

}
