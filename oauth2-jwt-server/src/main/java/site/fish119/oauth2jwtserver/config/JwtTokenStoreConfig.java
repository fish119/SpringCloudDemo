package site.fish119.oauth2jwtserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.oauth2jwtserver.config
 * @Author fish119
 * @Date 2020/2/26 12:56
 * @Version V1.0
 */
@Configuration
public class JwtTokenStoreConfig {
    @Bean
    @Primary
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 配置jwt使用的密钥
        jwtAccessTokenConverter.setSigningKey("test_key");
        return jwtAccessTokenConverter;
    }
}
