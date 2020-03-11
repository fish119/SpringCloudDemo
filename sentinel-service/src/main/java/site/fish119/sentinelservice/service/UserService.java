package site.fish119.sentinelservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;
import site.fish119.sentinelservice.po.Result;
import site.fish119.sentinelservice.po.User;

import java.util.List;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.sentinelservice.service
 * @Author fish119
 * @Date 2020/3/11 20:17
 * @Version V1.0
 */
@Primary
@FeignClient(value = "nacos-user-service", fallback = UserFallbackService.class)
public interface UserService {
    @GetMapping("/user/{id}")
    Result<User> getUser(@PathVariable Long id);

    @GetMapping("/user/listUsersByIds")
    Result<List<User>> listUsersByIds(@RequestParam List<Long> ids);

    @GetMapping("/user/getByUsername")
    Result<User> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    Result update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    Result delete(@PathVariable Long id);

}
