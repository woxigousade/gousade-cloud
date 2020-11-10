package com.gousade.cloud.discovery.consumer;

import com.gousade.common.ResponseResult;
import com.gousade.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }

    @RestController
    class EchoController {
        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string) {
            System.out.println("provider echo test");
            return "Hello provider Nacos Discovery " + string;
        }

        @RequestMapping(value = "/echopost", method = RequestMethod.POST)
        public String echopost(@RequestParam("str") String string) {
            log.info("provider echopost test");
            return "provider echopost Nacos Discovery " + string;
        }

        @RequestMapping(value = "/feignEntityPost", method = RequestMethod.POST)
        ResponseResult feignEntityPost(@RequestBody Employee employee) {
            log.info("{}", employee);
            return ResponseResult.renderSuccess().data("data", employee);
        }
    }

}
