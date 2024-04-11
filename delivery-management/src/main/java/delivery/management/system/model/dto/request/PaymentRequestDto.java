package delivery.management.system.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentRequestDto {

    @NotBlank(message = "Destination cannot be empty")
    private String destination;

    @Size(min = 16, max = 16, message = "Card number must be 16 digits")
    private String cardNumber;

    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;
}
