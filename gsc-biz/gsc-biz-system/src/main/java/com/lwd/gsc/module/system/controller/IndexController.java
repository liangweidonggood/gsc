package com.lwd.gsc.module.system.controller;

import com.lwd.gsc.module.system.model.User;
import com.lwd.gsc.module.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwd
 */
@Slf4j
@RestController
@RequestMapping("/system")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final UserService userService;

    @GetMapping
    public String index(){
        log.info("system-index");
        return "index";
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("system-getUserById");
        User user = userService.getUserById(id);
        log.info("system-getUserById2");
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
}
