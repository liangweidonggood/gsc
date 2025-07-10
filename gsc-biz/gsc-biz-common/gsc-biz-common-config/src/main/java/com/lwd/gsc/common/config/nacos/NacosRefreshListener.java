package com.lwd.gsc.common.config.nacos;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class NacosRefreshListener {

    public void onRefresh() {
        System.out.println("Nacos config refreshed!");
    }
}
