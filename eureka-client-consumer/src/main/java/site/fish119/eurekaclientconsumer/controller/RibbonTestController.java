package site.fish119.eurekaclientconsumer.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.controller
 * @Author fish119
 * @Date 2020/2/12 20:13
 * @Version V1.0
 */
@RestController
public class RibbonTestController {
    final
    RestTemplate restTemplate;

    public RibbonTestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/ribbonTest/{name}")
    public String ribbonTest(@PathVariable("name") String name){
        //这里直接写的是服务名。在ribbon中它会根据服务名来选择具体的服务实例，根据服务实例在请求的时候会用具体的url替换掉服务名
        return "Consumer Ribbon . "+restTemplate.getForObject("http://demo-eureka-client-provider/provider/" + name, String.class);
    }
}
