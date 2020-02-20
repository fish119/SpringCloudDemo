package site.fish119.eurekaclientconsumer.service.feignClient;

import org.springframework.stereotype.Component;
import site.fish119.eurekaclientconsumer.po.Result;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.eurekaclientconsumer.service.feignClient
 * @Author fish119
 * @Date 2020/2/20 11:23
 * @Version V1.0
 */
@Component
public class UserFallbackService implements UserService{
    @Override
    public Result getUser(Long id) {
        return new Result("调用失败，服务被Feign降级",500);
    }
}
