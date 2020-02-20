package site.fish119.eurekaclientconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import site.fish119.eurekaclientconsumer.po.Result;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.controller
 * @Author fish119
 * @Date 2020/2/20 10:49
 * @Version V1.0
 */
@RestController
@RequestMapping("/ribbon/user")
public class UserRibbonController {
    private final RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    public UserRibbonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "userFallBack")
    public Result getUser(@PathVariable Long id) {
        Result result = restTemplate.getForObject("http://demo-eureka-client-provider/user/{" + id + "}", Result.class, id);
        result.setMessage(result.getMessage()+"Port: "+port);
        return result;
    }

    //    Hystrix断路器的Callback方法
    public Result userFallBack(Long id) {
        return new Result<String>("Error", "userFallBack . Port:" + port, 500);
    }
}
