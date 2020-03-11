package site.fish119.sentinelservice.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import site.fish119.sentinelservice.po.Result;
import site.fish119.sentinelservice.po.User;

import java.util.List;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.sentinelservice.service
 * @Author fish119
 * @Date 2020/3/11 20:12
 * @Version V1.0
 */
@Component
public class UserFallbackService implements UserService{

    @GetMapping("/user/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        return new Result("调用失败，服务被降级", 500);
    }

    @GetMapping("/user/listUsersByIds")
    public Result<List<User>> listUsersByIds(@RequestParam List<Long> ids) {
        return new Result("调用失败，服务被降级", 500);
    }

    @GetMapping("/user/getByUsername")
    public Result<User> getByUsername(@RequestParam String username) {
        return new Result("调用失败，服务被降级", 500);
    }

    @PostMapping("/user/update")
    public Result update(@RequestBody User user) {
        return new Result("调用失败，服务被降级", 500);
    }

    @PostMapping("/user/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return new Result("调用失败，服务被降级", 500);
    }
}
