package com.example.demo.controller;

import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.Validated;

/**
 * @author airhead
 */
@Controller
@Mapping("/model")
public class ModelController {

    @Post
    @Mapping("add")
    public Object add(String modelId) {
        return modelId;
    }
}
