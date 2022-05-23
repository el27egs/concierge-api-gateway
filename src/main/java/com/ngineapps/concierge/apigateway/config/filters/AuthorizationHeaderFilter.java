package com.ngineapps.concierge.apigateway.config.filters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class AuthorizationHeaderFilter implements GatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("First pre filter" + exchange.getRequest().getURI());

            List<String> header = exchange.getRequest().getHeaders().getOrEmpty("Authorization");

            if (header.isEmpty()) {

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            log.debug("Authorization was " + header.get(0));

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("First post filter ");
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