package site.fish119.eurekaclientconsumer.service.feignClient;

import org.springframework.stereotype.Component;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.service.feignClient
 * @Author fish119
 * @Date 2020/2/13 14:27
 * @Version V1.0
 */
@Component
public class HystrixFallBack implements IFeignTest {
    @Override
    public String feignTest(String name) {
        return " fall back Feign. " + name;
    }
}
