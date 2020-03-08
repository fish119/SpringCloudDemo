package site.fish119.sentinelservice.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import site.fish119.sentinelservice.po.Result;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.sentinelservice.config
 * @Author fish119
 * @Date 2020/3/8 19:25
 * @Version V1.0
 */
public class CustomBlockHandler {

    public static Result customHandleException(BlockException exception) {
        return new Result("自定义限流信息", 200);
    }

}
