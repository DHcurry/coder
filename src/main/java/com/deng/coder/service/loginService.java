package com.deng.coder.service;

import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class loginService {
    @Autowired
    private UserMapper mapper;

    public User checkLoginStatus(HttpServletRequest request){
        // 获取request中的cookies
        // 验证登录状态
        // 返回当前登录的用户
        Cookie[] cookies  = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                // 遍历所有的cookie,找到其中键为token的
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    // 通过token去寻找用户
                    User user = mapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        return user;
                    } else
                        request.getSession().setAttribute("user", null);
                }
            }
        }
        return null;
    }
}
