package delivery.management.system.model.dao;

import lombok.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDetails {
    private WebAuthenticationDetailsSource webAuthenticationDetailsSource;
    private long id;

}
