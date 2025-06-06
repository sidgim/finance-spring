package com.glara.springcloud.msvc.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class MsvcUsersApplication {
    @Bean
    PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }
    public static void main(String[] args) {
        SpringApplication.run(MsvcUsersApplication.class, args);
    }

}
