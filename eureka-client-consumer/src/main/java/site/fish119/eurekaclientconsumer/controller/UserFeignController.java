package site.fish119.eurekaclientconsumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.eurekaclientconsumer.po.Result;
import site.fish119.eurekaclientconsumer.service.feignClient.UserService;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.controller
 * @Author fish119
 * @Date 2020/2/20 11:25
 * @Version V1.0
 */
@RestController
@RequestMapping("/feign/user")
public class UserFeignController {
    final
    UserService userService;

    @Value("${server.port}")
    String port;

    public UserFeignController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Long id) {
        Result result =  userService.getUser(id);
        result.setMessage(result.getMessage()+ " Port:"+port);
        return result;
    }
}
