package site.fish119.eurekaclientconsumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.eurekaclientconsumer.service.feignClient.IFeignTest;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.controller
 * @Author fish119
 * @Date 2020/2/12 20:17
 * @Version V1.0
 */
@RestController
@RequestMapping("/feign/test")
public class FeignController {
    final IFeignTest iFeignTest;

    @Value("${server.port}")
    String port;

    public FeignController(IFeignTest iFeignTest) {
        this.iFeignTest = iFeignTest;
    }

    @RequestMapping("/{name}")
    public String feignTest(@PathVariable("name") String name) {
        return "Consumer Feign . Port :" + port + "." +iFeignTest.feignTest(name);
    }
}
