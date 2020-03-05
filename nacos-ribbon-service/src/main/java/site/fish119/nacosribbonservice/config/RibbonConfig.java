package site.fish119.nacosribbonservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.consulribbonservice.config
 * @Author fish119
 * @Date 2020/3/1 13:05
 * @Version V1.0
 */
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
