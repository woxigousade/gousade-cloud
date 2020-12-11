package com.gousade.cloud.discovery.consumer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(@PathVariable String str) {
        System.out.println("test");
        return "test2";
    }
}
