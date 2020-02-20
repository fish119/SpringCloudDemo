package site.fish119.eurekaclientconsumer.service.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.fish119.eurekaclientconsumer.po.Result;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.service.feignClient
 * @Author fish119
 * @Date 2020/2/20 11:23
 * @Version V1.0
 */
@Primary
@FeignClient(name = "demo-eureka-client-provider", fallback = UserFallbackService.class)
public interface UserService {
    @GetMapping("/user/{id}")
    Result getUser(@PathVariable Long id);
}
