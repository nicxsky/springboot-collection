package com.example.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现服务的注册与发现
 *
 * @author daoqing
 */
@RestController
@RequestMapping("discovery")
public class DiscoveryController {

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery1 " + string;
    }

    @RequestMapping(value = "/echo2/{string}", method = RequestMethod.GET)
    public String echo2(@PathVariable String string) {
        return "Hello Nacos Discovery2 " + string;
    }

}
