package com.example.demo;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;
import org.smartboot.http.server.HttpServerConfiguration;

@SolonMain
public class DemoServerApp {
    public static void main(String[] args) {
        Solon.start(DemoServerApp.class, args, solonApp -> {
            solonApp.onEvent(HttpServerConfiguration.class, server -> {
                server.debug(true);
            });
        });
    }
}