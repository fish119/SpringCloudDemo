package site.fish119.sentinelservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import site.fish119.sentinelservice.po.Result;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.sentinelservice.controller
 * @Author fish119
 * @Date 2020/3/11 19:55
 * @Version V1.0
 */
@RestController
@RequestMapping("/breaker")
public class CircleBreakerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CircleBreakerController.class);

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    private final RestTemplate restTemplate;

    public CircleBreakerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handleFallback")
    public Result fallback(@PathVariable Long id) {
        Result forObject = restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
        System.out.println(forObject);
        return forObject;
    }

    @GetMapping("/fallbackException/{id}")
    @SentinelResource(value = "fallbackException", fallback = "handleFallback2", exceptionsToIgnore = {NullPointerException.class})
    public Result fallbackException(@PathVariable Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }

        return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
    }

    public Result handleFallback(Long id) {
        return new Result("服务降级返回", 200);
    }

    public Result handleFallback2(Long id, Throwable e) {
        LOGGER.error("handleFallback2 id:{},throwable class:{}", id, e.getClass());
        return new Result("服务降级返回", 200);
    }
}
