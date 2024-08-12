package com.example.demo.controller;

import org.noear.solon.annotation.*;

@Controller
@Mapping("/model")
public class ModelController {
    @Mapping("add")
    public Object add(String modelId) {
        return modelId;
    }
}
