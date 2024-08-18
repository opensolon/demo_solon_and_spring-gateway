package com.demo.gateway;

import org.noear.snack.ONode;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class FilterImpl implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        return -9;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();
        ServerHttpResponse resp = exchange.getResponse();
        String token = req.getHeaders().getFirst("TOKEN");

        if ("test".equals(token)) {
            //如果没有 TOKEN 表示失败
            Map<String, String> result = new HashMap<>();
            result.put("code", "401");
            result.put("description", "签权失败");

            String resultStr = ONode.stringify(result);

            //resp.setStatusCode(HttpStatus.UNAUTHORIZED);
            resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            DataBuffer buffer = resp.bufferFactory().wrap(resultStr.getBytes(StandardCharsets.UTF_8));
            return resp.writeWith(Mono.just(buffer));
        }

        return chain.filter(exchange);
    }
}
