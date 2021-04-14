package com.example.springcloud.alibaba.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 实现服务的注册与发现
 *
 * @author daoqing
 */
@RestController
//@RequestMapping(path = "/discovery")
@RequestMapping("discovery")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    /**
     * curl http://localhost:8081/nacos/discovery/get?serviceName=example，此时返回为空 JSON 数组[]
     *
     * post 注册临时实例(ephemeral=true表示永久实例)
     * http://192.168.164.128:8848/nacos/v1/ns/instance?serviceName=example&ip=127.0.0.1&port=8081&ephemeral=false
     *
     * 再次调用 http://localhost:8081/nacos/discovery/get?serviceName=example
     * 返回：
     * [
     *     {
     *         "instanceId": "127.0.0.1#8081#DEFAULT#DEFAULT_GROUP@@example",
     *         "ip": "127.0.0.1",
     *         "port": 8081,
     *         "weight": 1.0,
     *         "healthy": false,
     *         "enabled": true,
     *         "ephemeral": true,
     *         "clusterName": "DEFAULT",
     *         "serviceName": "DEFAULT_GROUP@@example",
     *         "metadata": {},
     *         "instanceHeartBeatInterval": 5000,
     *         "instanceHeartBeatTimeOut": 15000,
     *         "ipDeleteTimeout": 30000,
     *         "instanceIdGenerator": "simple"
     *     }
     * ]
     *
     * 注：此时nacos服务管理->服务列表的这个实例会消失，会直接从列表中被删除
     *
     * 临时和持久化的区别主要在健康检查失败后的表现，持久化实例健康检查失败后会被标记成不健康，而临时实例会直接从列表中被删除。
     * 这个特性比较适合那些需要应对流量突增，而弹性扩容的服务，当流量降下来后这些实例自己销毁自己就可以了，不用再去nacos里手动调用注销实例。持久化以后，可以实时看到健康状态，便于做后续的告警、扩容等一系列处理
     *
     *
     * @param serviceName
     * @return
     * @throws NacosException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}
