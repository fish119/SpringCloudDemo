package site.fish119.oauth2jwtserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import site.fish119.oauth2jwtserver.po.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.oauth2jwtserver.service
 * @Author fish119
 * @Date 2020/2/26 11:41
 * @Version V1.0
 */
@Service
public class UserService implements UserDetailsService {
    private List<User> userList;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();

        userList.add(new User("fish",password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("andy",password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("mark",password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("wrong user name or password");
        }
    }
}
