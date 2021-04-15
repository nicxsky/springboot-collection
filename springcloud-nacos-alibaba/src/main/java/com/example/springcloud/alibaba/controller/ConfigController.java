package com.example.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现配置的动态变更
 * <br>
 * 通过 Spring Cloud 原生注解 @RefreshScope 实现配置自动更新
 *
 * @author daoqing
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }

    /**
     * curl -X POST "http://192.168.164.128:8848/nacos/v1/cs/configs?dataId=my-service-provider&group=DEFAULT_GROUP&content=useLocalCache=true"
     *
     * curl http://localhost:8082/cloud-nacos/config/get
     *
     * curl -X POST "http://192.168.164.128:8848/nacos/v1/cs/configs?dataId=my-service-provider&group=DEFAULT_GROUP&content=useLocalCache=false"
     *
     */
}
