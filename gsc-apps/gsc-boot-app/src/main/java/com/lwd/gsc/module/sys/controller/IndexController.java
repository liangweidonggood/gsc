package com.lwd.gsc.module.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "hello world";
    }
}
