package site.fish119.sentinelservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.sentinelservice.config.CustomBlockHandler;
import site.fish119.sentinelservice.po.Result;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.sentinelservice.controller
 * @Author fish119
 * @Date 2020/3/8 19:24
 * @Version V1.0
 */
@RestController
@RequestMapping("/rateLimit")
public class RateLimitController {
    /**
     * 按资源名称限流，需要指定限流处理逻辑
     *
     * @return
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public Result byResource() {
        return new Result("按资源名称限流", 200);
    }

    /**
     * 按url限流，有默认的限流处理逻辑
     *
     * @return
     */
    @GetMapping("/byUrl")
    @SentinelResource(blockHandler = "handleException")
    public Result byUrl() {
        return new Result("按url限流", 200);
    }

    public Result handleException(BlockException exception) {
        return new Result(exception.getClass().getCanonicalName(), 200);
    }

    @GetMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler", blockHandler = "customHandleException", blockHandlerClass = {CustomBlockHandler.class})
    public Result blockHandler() {
        return new Result("限流成功", 200);
    }
}
