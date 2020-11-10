package com.gousade.cloud.discovery.consumer;

import com.gousade.common.ResponseResult;
import com.gousade.entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author woxigsd@gmail.com
 * @date 2020-11-10 10:23:33
 * @description
 */
@FeignClient(name = "service-provider")
@Component
public interface EchoService {

    @GetMapping(value = "/echo/{str}")
    String echo(@PathVariable("str") String str);

    @RequestMapping(value = "/echopost", method = RequestMethod.POST)
    String echopost(@RequestParam("str") String str);

    @RequestMapping(value = "/feignEntityPost", method = RequestMethod.POST)
    ResponseResult feignEntityPost(/*@RequestBody*/ Employee employee);
}
