package com.spring.security.method2;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class JdbcSecurityConfig {

    /*
     * This method creates a JdbcUserDetailsManager bean that uses the provided
     * DataSource to manage user details.
     * It allows for user authentication and authorization based on data stored in a
     * relational database.
     */

    //@Bean
    public UserDetailsManager userDetailsManager(DataSource datasource) {
        return new JdbcUserDetailsManager(datasource);
    }

    /*
     * This method configures the security filter chain for the application.
     * It defines the authorization rules for different HTTP methods and endpoints.
     * The rules specify which roles are allowed to access specific endpoints
     * and what actions (GET, POST, DELETE, PUT, PATCH) can be performed by those
     * roles.
     */
    //@Bean
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
