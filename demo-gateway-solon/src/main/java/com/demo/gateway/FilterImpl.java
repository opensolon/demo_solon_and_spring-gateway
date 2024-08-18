package com.demo.gateway;

import org.noear.solon.annotation.Component;
import org.noear.solon.cloud.gateway.CloudGatewayFilter;
import org.noear.solon.cloud.gateway.exchange.ExContext;
import org.noear.solon.cloud.gateway.exchange.ExFilterChain;
import reactor.core.publisher.Mono;

@Component
public class FilterImpl implements CloudGatewayFilter {
    @Override
    public Mono<Void> doFilter(ExContext ctx, ExFilterChain chain) {
        System.out.println("demo....");

        return chain.doFilter(ctx);
    }
}
