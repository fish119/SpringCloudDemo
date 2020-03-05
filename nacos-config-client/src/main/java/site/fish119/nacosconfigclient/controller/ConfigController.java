package site.fish119.nacosconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.nacosconfigclient.controller
 * @Author fish119
 * @Date 2020/3/5 20:56
 * @Version V1.0
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public Object getConfigInfo() {
        return configInfo;
    }
}
