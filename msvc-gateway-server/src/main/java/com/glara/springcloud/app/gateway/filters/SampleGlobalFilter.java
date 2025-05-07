package com.glara.springcloud.app.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SampleGlobalFilter implements GlobalFilter, Ordered {
    private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Global filter: Request URI: {}", exchange.getRequest().getURI());
        
        exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.add("X-Request-Id", "123456");
            httpHeaders.add("X-Request-Name", "glara");
        });

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {;
            logger.info("Global filter: Response status code: {}", exchange.getResponse().getStatusCode());
            exchange.getResponse().getCookies().add("usr", ResponseCookie.from("usr", "glara").build());
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        }));
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
