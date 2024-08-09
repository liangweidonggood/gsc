package com.lwd.gsc.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwd
 */
@Slf4j
@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
