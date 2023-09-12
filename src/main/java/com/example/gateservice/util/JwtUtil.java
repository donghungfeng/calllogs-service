package com.example.gateservice.util;


import com.example.gateservice.config.jwt.JwtTokenProvider;
import com.example.gateservice.model.User;
import com.example.gateservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtUtil {
    @Autowired
    UserService userService;

    public User getUserByJwt(String jwt){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String bearerToken = getJwtFromRequest(jwt);
        String userName = jwtTokenProvider.getAccountUserNameFromJWT(bearerToken);
        return userService.getByUserName(userName);
    }

    private String getJwtFromRequest(String bearerToken) {
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
