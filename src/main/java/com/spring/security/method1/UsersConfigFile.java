package com.spring.security.method1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class UsersConfigFile {

    /*
     * This method creates an in-memory user details manager with two users:
     * "rama" with roles "USER" and "ADMIN", and "krishna" with roles "USER",
     * "ADMIN", and "MANAGER".
     * The passwords are stored in plain text (using "{noop}" prefix).
     */
    // @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails rama = User.builder().username("rama").password("{noop}1234").roles("USER", "ADMIN").build();
        UserDetails krishna = User.builder().username("krishna").password("{noop}1234")
                .roles("USER", "ADMIN", "MANAGER").build();
        return new InMemoryUserDetailsManager(rama, krishna);
    }
    /*
     * This method configures the security filter chain for the application.
     * It defines the authorization rules for different HTTP methods and endpoints.
     * The rules specify which roles are allowed to access specific endpoints
     * and what actions (GET, POST, DELETE, PUT, PATCH) can be performed by those
     * roles.
     * The HTTP Basic authentication is enabled, and CSRF protection is disabled.
     */

    // @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String admin = "ADMIN";
        String user = "USER";
        String manager = "MANAGER";
        String students = "/students";
        String allEndpoints = "/students/**";

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, students).hasAnyRole(user, admin, manager)
                .requestMatchers(HttpMethod.GET, allEndpoints).hasAnyRole(user, admin, manager)
                .requestMatchers(HttpMethod.POST, students).hasAnyRole(admin, manager)
                .requestMatchers(HttpMethod.DELETE, allEndpoints).hasRole(admin)
                .requestMatchers(HttpMethod.PUT, allEndpoints).hasAnyRole(admin, manager)
                .requestMatchers(HttpMethod.PUT, students).hasAnyRole(admin, manager)
                .requestMatchers(HttpMethod.PATCH, allEndpoints).hasAnyRole(admin, manager)

        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();

    }
}

