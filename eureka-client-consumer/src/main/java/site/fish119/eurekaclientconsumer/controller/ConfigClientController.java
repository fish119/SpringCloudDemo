package site.fish119.eurekaclientconsumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.controller
 * @Author fish119
 * @Date 2020/2/14 19:12
 * @Version V1.0
 * config client配置，使用时打开注释
 */
@RestController
//@RefreshScope
public class ConfigClientController {
//    @Value("${config.info}")
////    private String configInfo;
////
////    @GetMapping("/configInfo")
////    public String getConfigInfo(){
////        return configInfo;
////    }
}
