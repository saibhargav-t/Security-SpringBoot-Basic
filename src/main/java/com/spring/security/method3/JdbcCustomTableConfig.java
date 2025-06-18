package com.spring.security.method3;

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
public class JdbcCustomTableConfig {

    //@Bean
    public UserDetailsManager userDetailsManager(DataSource datasource) {
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(datasource);
        // Query to retrieve user by id
        theUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        // Query to retrieve authorities by user id
        theUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return theUserDetailsManager;
    }

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
