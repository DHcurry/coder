package com.deng.coder.controller;

import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class indexController {
    @Autowired
    private UserMapper mapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        // 获取request中的cookies
        Cookie[] cookies  = request.getCookies();
        for (Cookie cookie: cookies) {
            // 遍历所有的cookie,找到其中键为token的
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                // 通过token去寻找用户
                User user = mapper.findByToken(token);
                if(user != null) {
                    request.getSession().setAttribute("user", user);
                    break;
                }
                else
                    request.getSession().setAttribute("user",null);
            }
        }
        return "index";
    }

}
