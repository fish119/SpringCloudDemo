package site.fish119.consuluserservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import site.fish119.consuluserservice.po.Result;
import site.fish119.consuluserservice.po.User;
import site.fish119.consuluserservice.service.UserService;

import java.util.List;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.consuluserservice.controller
 * @Author fish119
 * @Date 2020/3/1 12:58
 * @Version V1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${server.port}")
    String port;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {
        userService.insert(user);
        return new Result("操作成功，from port:"+port, 200);
    }

    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        LOGGER.info("根据id获取用户信息，用户名称为：{}",user.getUsername());
        return new Result<User>(user,"操作成功，from port:"+port,200);
    }

    @GetMapping("/listUsersByIds")
    public Result<List<User>> listUsersByIds(@RequestParam List<Long> ids) {
        List<User> userList= userService.listUsersByIds(ids);
        LOGGER.info("根据ids获取用户信息，用户列表为：{}",userList);
        return new Result<List<User>>(userList);
    }

    @GetMapping("/getByUsername")
    public Result<User> getByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        return new Result<User>(user);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return new Result("操作成功", 200);
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        userService.delete(id);
        return new Result("操作成功", 200);
    }
}