package delivery.management.system.config;

import delivery.management.system.model.entity.Driver;
import delivery.management.system.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static delivery.management.system.model.enums.PermissionType.*;
import static delivery.management.system.model.enums.RoleType.*;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        String LOGIN = "users/authentication";
        String LOGOUT = "users/logout";
        String SWAGGER_UI = "/swagger-ui/**";
        String API_DOCS = "/v3/api-docs/**";
        String FIND_BY_ID = "find-by-id";
        String UPDATED = "updated";
        String RESET_PASSWORD = "/users/reset-password";
        String RENEW_PASSWORD = "/users/renew-password/{username}";
        String USERS_CUSTOMERS = "users/customer";
        String CONFIRMATION = "/users/confirmation";
        String PRODUCT_ID = "product/{product-id}";
        String PRODUCT_PHOTO_UPLOADING = "product/uploading/{product-id}";
        String PRODUCT = "product/";
        String DRIVERS = "drivers/";
        String DRIVERS_ORDER = "drivers/order";
        String DRIVERS_ORDER_DELIVERED = "drivers/order/delivered";
        String CATEGORY_ID = "category/{category-id}";
        String CATEGORY = "category/";
        String ROLE = "admin/roles/";
        String CUSTOMER_CART_ADD_PRODUCT = "customers/{product-id}/cart";
        String CUSTOMER_CART_CHECKOUT = "customers/cart/checkout";
        String CUSTOMER_ORDERS = "customers/orders";
        String CUSTOMERS = "customers/";
        String CUSTOMERS_CART = "customers/cart";
        String CUSTOMERS_CART_DELETE_PRODUCT_ID = "customers/cart/{product-id}";
        String ADMINS = "admins/";
        String DRIVERS_UPDATED = "drivers-updated/{id}";
        String DRIVERS_BLOCK = "drivers-block/{id}";
        String DRIVERS_ACTIVE = "drivers-active/{id}";
        String ORDERS = "orders";
        String ORDERS_ID = "orders/{order-id}";
        String DRIVERS_ID = "drivers/find-by-id/{id}";
        String DRIVERS_APPEALS = "drivers-appeals";
        String DASHBOARD = "dashboard";
        String CUSTOMER_ID = "customer/find-by-id/{id}";
        String DRIVERS_DELETED = "drivers-deleted/{id}";
        String CUSTOMER_DELETED = "customer-deleted/{id}";




        http
                .csrf(CsrfConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurer()))
                .sessionManagement(sessionConfigure -> sessionConfigure.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> {

                    request.requestMatchers(SWAGGER_UI).permitAll();
                    request.requestMatchers(API_DOCS).permitAll();

                    request.requestMatchers(HttpMethod.POST, LOGIN).permitAll();
                    request.requestMatchers(HttpMethod.POST, LOGOUT).authenticated();

                    request.requestMatchers(HttpMethod.PUT, RESET_PASSWORD).permitAll();
                    request.requestMatchers(HttpMethod.POST, RENEW_PASSWORD).permitAll();
                    request.requestMatchers(HttpMethod.POST, USERS_CUSTOMERS).permitAll();
                    request.requestMatchers(HttpMethod.POST, CONFIRMATION).permitAll();


                    request.requestMatchers(HttpMethod.GET, PRODUCT_ID).hasAnyRole(ADMIN.name(), CUSTOMER.name(), DRIVER.name());
                    request.requestMatchers(HttpMethod.PUT, PRODUCT_ID).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.DELETE, PRODUCT_ID).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.POST, PRODUCT_PHOTO_UPLOADING).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, PRODUCT).hasAnyRole(ADMIN.name(), CUSTOMER.name(), DRIVER.name());
                    request.requestMatchers(HttpMethod.POST, PRODUCT).hasRole(ADMIN.name());

                    request.requestMatchers(HttpMethod.PUT, DRIVERS + UPDATED).hasRole(DRIVER.name());
                    request.requestMatchers(HttpMethod.POST, DRIVERS).permitAll();
                    request.requestMatchers(HttpMethod.GET, DRIVERS + FIND_BY_ID).hasRole(DRIVER.name());
                    request.requestMatchers(HttpMethod.GET, DRIVERS_ORDER).hasRole(DRIVER.name());
                    request.requestMatchers(HttpMethod.PATCH, DRIVERS_ORDER_DELIVERED).hasRole(DRIVER.name());

                    request.requestMatchers(HttpMethod.GET, CATEGORY_ID).hasAnyRole(ADMIN.name(), CUSTOMER.name(), DRIVER.name());
                    request.requestMatchers(HttpMethod.PUT, CATEGORY_ID).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.DELETE, CATEGORY_ID).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, CATEGORY).hasAnyRole(ADMIN.name(), CUSTOMER.name(), DRIVER.name());
                    request.requestMatchers(HttpMethod.POST, CATEGORY).hasRole(ADMIN.name());

                    request.requestMatchers(HttpMethod.POST, ROLE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.PATCH, ROLE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ROLE).hasRole(ADMIN.name());

                    request.requestMatchers(HttpMethod.POST, CUSTOMER_CART_ADD_PRODUCT).hasRole(CUSTOMER.name());
                    request.requestMatchers(HttpMethod.POST, CUSTOMER_CART_CHECKOUT).hasRole(CUSTOMER.name());
                    request.requestMatchers(HttpMethod.GET, CUSTOMER_ORDERS).hasRole(CUSTOMER.name());
                    request.requestMatchers(HttpMethod.GET, CUSTOMERS + FIND_BY_ID).hasRole(CUSTOMER.name());
                    request.requestMatchers(HttpMethod.GET, CUSTOMERS_CART).hasRole(CUSTOMER.name());
                    request.requestMatchers(HttpMethod.DELETE, CUSTOMERS_CART_DELETE_PRODUCT_ID).hasRole(CUSTOMER.name());

                    request.requestMatchers(HttpMethod.PUT, ADMINS + DRIVERS_UPDATED).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.PATCH, ADMINS + DRIVERS_BLOCK).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.PATCH, ADMINS + DRIVERS_ACTIVE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + ORDERS).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + ORDERS_ID).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + DRIVERS).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + DRIVERS_ID).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + DRIVERS_APPEALS).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + DASHBOARD).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + CUSTOMERS).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ADMINS + CUSTOMER_ID).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.DELETE, ADMINS + DRIVERS_DELETED).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.DELETE, ADMINS + CUSTOMER_DELETED).hasRole(ADMIN.name());

                    request.anyRequest().authenticated();

                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    private UrlBasedCorsConfigurationSource corsConfigurer() {
        CorsConfiguration corsConfigure = new CorsConfiguration();
        corsConfigure.addAllowedOrigin("*");
        corsConfigure.addAllowedHeader("*");
        corsConfigure.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfigure);

        return corsConfigurationSource;
    }
}