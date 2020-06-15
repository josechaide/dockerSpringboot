package com.aubay.docker.demo.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/docker/hello")
// hola
public class HelloResource {
    @GetMapping
    public String hello(){
        return "Hello Jenkins Integration";
    }
}
