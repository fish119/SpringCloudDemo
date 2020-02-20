package site.fish119.eurekaclientprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.eurekaclientprovider.po.Result;
import site.fish119.eurekaclientprovider.po.User;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientprovider
 * @Author fish119
 * @Date 2020/2/20 10:45
 * @Version V1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        User user = new User(id, "User "+id, "password "+id);
        return new Result<User>(user);
    }
}
