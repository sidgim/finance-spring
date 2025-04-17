package com.glara.msvc_accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcAccountsApplication.class, args);
    }

}
