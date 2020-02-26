package site.fish119.oauth2jwtserver.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @Project SpringCloudDemo
 * @Package site.fish119.oauth2server.controller
 * @Author fish119
 * @Date 2020/2/23 20:24
 * @Version V1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = subAfter(header, "Bearer ", false);
        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    public static String subAfter(CharSequence string, CharSequence separator, boolean isLastSeparator) {
        if(string == null || string.length() == 0){
            return null == string ? null : string.toString();
        }
        if (separator == null) {
            return "";
        }
        final String str = string.toString();
        final String sep = separator.toString();
        final int pos = isLastSeparator ? str.lastIndexOf(sep) : str.indexOf(sep);
        if (-1 == pos || (string.length() - 1) == pos) {
            return " ";
        }
        return str.substring(pos + separator.length());
    }
}
