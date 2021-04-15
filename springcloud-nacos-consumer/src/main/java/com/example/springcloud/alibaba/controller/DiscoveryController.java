package com.example.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 实现服务的注册与发现
 *
 * @author daoqing
 */
@RestController
@RequestMapping("consumer")
public class DiscoveryController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public DiscoveryController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://my-service-provider/cloud-nacos/discovery/echo/" + str, String.class);
    }

}
