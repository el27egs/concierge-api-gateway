/*
 *     Copyright 2022-Present Ngine Apps @ http://www.ngingeapps.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ngineapps.concierge.apigateway.config.filters;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationHeaderFilter
    implements GatewayFilterFactory<AuthorizationHeaderFilter.Config> {

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      List<String> header = exchange.getRequest().getHeaders().getOrEmpty("Authorization");

      if (header.isEmpty()) {

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }

      log.info("Authorization header:  " + header.get(0).substring(0, 15) + "...");

      return chain.filter(exchange);
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
