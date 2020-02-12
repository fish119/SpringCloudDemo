package site.fish119.eurekaclientconsumer.service.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.service.feignClient
 * @Author fish119
 * @Date 2020/2/12 20:16
 * @Version V1.0
 */
@FeignClient(name = "demo-eureka-client-provider")
public interface IFeignTest {
    @RequestMapping(value = "/provider/{name}")
    String feignTest(@PathVariable String name);
}
