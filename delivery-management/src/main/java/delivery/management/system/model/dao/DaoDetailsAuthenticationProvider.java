package delivery.management.system.model.dao;

import delivery.management.system.model.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class DaoDetailsAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails userDetails) {

        UsernamePasswordAuthenticationToken successAuthentication = (UsernamePasswordAuthenticationToken) super.createSuccessAuthentication(principal, authentication, userDetails);
        User user = (User) userDetails;
        successAuthentication.setDetails(new AuthenticationDetails(null, user.getId()));
        return successAuthentication;
    }
}
