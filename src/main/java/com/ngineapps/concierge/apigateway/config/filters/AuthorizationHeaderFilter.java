package com.ngineapps.concierge.apigateway.config.filters;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthorizationHeaderFilter implements GatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("First pre filter" + exchange.getRequest().getURI());

            List<String> header = exchange.getRequest().getHeaders().getOrEmpty("Authorization");

            if (header.isEmpty()) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("First post filter ");
            }));
        };
    }
    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        Config c = new Config();
        return c;
    }

    public static class Config {}

}