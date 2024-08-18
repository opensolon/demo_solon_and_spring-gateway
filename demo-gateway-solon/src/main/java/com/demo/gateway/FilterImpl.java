package com.demo.gateway;

import io.vertx.core.buffer.Buffer;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.cloud.gateway.CloudGatewayFilter;
import org.noear.solon.cloud.gateway.exchange.ExContext;
import org.noear.solon.cloud.gateway.exchange.ExFilterChain;
import org.noear.solon.core.handle.Result;
import reactor.core.publisher.Mono;

@Component
public class FilterImpl implements CloudGatewayFilter {
    @Override
    public Mono<Void> doFilter(ExContext ctx, ExFilterChain chain) {
        String token = ctx.rawHeader("TOKEN");

        if ("test".equals(token)) {
            //如果没有 TOKEN 表示失败
            String resultStr = ONode.stringify(Result.failure(401, "签权失败"));

            //ctx.newResponse().status(401);
            ctx.newResponse().header("Content-Type", "application/json;charset=UTF-8");
            ctx.newResponse().body(Buffer.buffer(resultStr));
            return Mono.empty();
        }


        return chain.doFilter(ctx);
    }
}
