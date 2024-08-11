package com.example.demo;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;

@SolonMain
public class DemoServerApp {
    public static void main(String[] args) {
        Solon.start(DemoServerApp.class, args);
    }
}