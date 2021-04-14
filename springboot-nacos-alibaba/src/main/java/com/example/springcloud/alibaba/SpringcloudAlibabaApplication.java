package com.example.springcloud.alibaba;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用 @NacosPropertySource 加载 dataId 为 example 的配置源，并开启自动更新
 * dataId：这个属性是需要在Nacos中配置的Data Id
 * autoRefreshed：为true的话开启自动更新
 *
 * @author daoqing
 */
@SpringBootApplication
@NacosPropertySource(dataId = "springcloud-nacos-hello", autoRefreshed = true)
@RestController
public class SpringcloudAlibabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudAlibabaApplication.class, args);
    }

    @NacosValue(value = "${service.name:test}", autoRefreshed = true)
    private String serverName;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    //@ResponseBody
    public String get() {
        return serverName;
    }
}
