package com.example.docker.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public String test(@RequestBody(required = false) String name) {
        return "Hello: " + name;
    }
}
