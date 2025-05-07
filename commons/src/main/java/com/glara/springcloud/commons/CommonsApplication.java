package com.glara.springcloud.commons;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootConfiguration
public class CommonsApplication {

}
