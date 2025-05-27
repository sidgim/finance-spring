package com.glara.springcloud.app.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(ex -> ex
                        .pathMatchers("/authorized", "/logout").permitAll()
                        .pathMatchers("/user/**").permitAll()
                        .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .oauth2Login(withDefaults())
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(rs -> rs.jwt(withDefaults()))
                .build();
    }
}
