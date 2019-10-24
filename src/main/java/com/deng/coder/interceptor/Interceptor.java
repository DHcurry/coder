package com.deng.coder.interceptor;

import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class Interceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
                        break;
                    } else
                        request.getSession().setAttribute("user", null);
                }
            }
        }
        return true;
    }
}
