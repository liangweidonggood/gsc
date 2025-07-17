package com.lwd.gsc.doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lwd
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DocApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocApplication.class, args);
    }
}
