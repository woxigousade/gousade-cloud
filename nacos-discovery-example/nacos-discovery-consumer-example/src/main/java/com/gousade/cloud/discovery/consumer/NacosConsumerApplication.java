package com.gousade.cloud.discovery.consumer;

import com.gousade.common.ResponseResult;
import com.gousade.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@ComponentScan(basePackages = {"com.gousade"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RestController
    public class TestController {

        private final RestTemplate restTemplate;
        @Autowired
        private EchoService echoService;

        @Autowired
        public TestController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @RequestMapping(value = "/echo-rest/{str}", method = RequestMethod.GET)
        public String rest(@PathVariable String str) {
            System.out.println("test");
            return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
        }

        @RequestMapping(value = "/echo-feign/{str}", method = RequestMethod.GET)
        public String feign(@PathVariable String str) {
            return echoService.echo(str);
        }

        @RequestMapping(value = "/echo-feign-post", method = RequestMethod.POST)
        public String feignpost(String str) {
            return echoService.echopost(str);
        }

        @RequestMapping(value = "/echo-feign-entity-post", method = RequestMethod.POST)
        public ResponseResult feignEntityPost(@RequestBody Employee employee) {
            return echoService.feignEntityPost(employee);
        }
    }

}
