package com.lwd.gsc.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456"; // 原始密码
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("加密后的密码: " + encodedPassword);
    }
}
