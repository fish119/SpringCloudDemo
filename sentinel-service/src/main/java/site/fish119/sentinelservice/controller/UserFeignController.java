package site.fish119.sentinelservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.fish119.sentinelservice.po.Result;
import site.fish119.sentinelservice.po.User;
import site.fish119.sentinelservice.service.UserService;

import java.util.List;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.sentinelservice.controller
 * @Author fish119
 * @Date 2020/3/11 20:14
 * @Version V1.0
 */
@RestController
@RequestMapping("/user")
public class UserFeignController {
    private final UserService userService;

    public UserFeignController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/listUsersByIds")
    public Result<List<User>> listUsersByIds(@RequestParam List<Long> ids) {
        return userService.listUsersByIds(ids);
    }

    @GetMapping("/getByUsername")
    public Result<User> getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
