package site.fish119.oauth2jwtserver.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import site.fish119.oauth2jwtserver.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 授权服务器配置
 * @Project SpringCloudDemo
 * @Package site.fish119.oauth2jwtserver.config
 * @Author fish119
 * @Date 2020/2/26 11:40
 * @Version V1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final TokenStore tokenStore;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final JwtTokenEnhancer jwtTokenEnhancer;

    public AuthorizationServerConfig(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService, @Qualifier("jwtTokenStore") TokenStore tokenStore, JwtAccessTokenConverter jwtAccessTokenConverter, JwtTokenEnhancer jwtTokenEnhancer) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenStore = tokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }

    /**
     * 使用密码模式需要配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        // 配置jwt内容增强器
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService)
                //配置令牌存储策略
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 配置client_id
                .withClient("clientId")
                // 配置client_secret
                .secret(passwordEncoder.encode("123456"))
                // 配置访问token的有效期
                .accessTokenValiditySeconds(3600)
                // 配置刷新token的有效期
                .refreshTokenValiditySeconds(864000)
                // 配置redirect_uri,用于授权成功后的跳转
                .redirectUris("http://192.168.0.1")
                // 单点登录时配置
//                .redirectUris("http://localhost:9501/login")
                // 自动授权配置
                .autoApprove(true)
                // 配置申请的权限范围
                .scopes("all")
                // 配置grant_type,表示授权类型
                .authorizedGrantTypes("authorization_code", "password", "refresh_token");
    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        // 获取密钥需要身份认证，使用单点登录时必须配置
//        security.tokenKeyAccess("isAuthenticated()");
//    }
}
