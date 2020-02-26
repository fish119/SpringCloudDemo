package site.fish119.oauth2jwtserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Description:使用redis存储token的配置
 * @Project SpringCloudDemo
 * @Package site.fish119.oauth2jwtserver.config
 * @Author fish119
 * @Date 2020/2/26 11:38
 * @Version V1.0
 */
@Configuration
public class RedisTokenStoreConfig {
    final RedisConnectionFactory redisConnectionFactory;

    public RedisTokenStoreConfig(RedisConnectionFactory redisConnectionFactory){
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
}
