package site.fish119.eurekaclientprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientprovider
 * @Author fish119
 * @Date 2020/2/12 14:54
 * @Version V1.0
 */
@RestController
public class TestController {
    @Value("${server.port}")
    String port;

    @RequestMapping("/provider/{name}")
    public String index(@PathVariable("name") String name) {
        return String.format("Provider , hello %s , from port=%s", name, port);
    }

}
