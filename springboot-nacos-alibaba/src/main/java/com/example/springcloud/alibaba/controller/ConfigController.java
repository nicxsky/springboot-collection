package com.example.springcloud.alibaba.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现配置的动态变更
 *
 * @author daoqing
 */
@RestController
public class ConfigController {

    /**
     * 在使用Nacos做配置中心后，需要使用@NacosValue注解获取配置，使用方式与@Value一样
     */
    @NacosValue(value = "${test.properties.cache.enable:false}", autoRefreshed = true)
    private boolean cacheEnable;

    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean get() {
        return cacheEnable;
    }
}
