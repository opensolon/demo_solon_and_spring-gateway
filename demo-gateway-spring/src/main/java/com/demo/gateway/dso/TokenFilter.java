package com.demo.gateway.dso;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The class Auth header filter.
 *
 * @author
 */
@Slf4j
//@Component
public class TokenFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse resp = exchange.getResponse();

        try {
            Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                httpHeader.set("app", "0");
            };
            ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders).build();
            exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return authError(resp, HttpStatus.UNAUTHORIZED, "异常", "异常");
        }
    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     * 认证错误输出
     *
     * @param resp 响应对象
     * @param msg  错误信息
     * @return
     */
    private Mono<Void> authError(ServerHttpResponse resp, HttpStatus httpStatus, String error, String msg) {
        resp.setStatusCode(httpStatus);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        Map<String, Object> ret = new HashMap<>();
        ret.put("error", error);
        ret.put("msg", msg);
        ret.put("code", httpStatus.value());
        String returnStr = JSONObject.toJSONString(ret);
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }
}
