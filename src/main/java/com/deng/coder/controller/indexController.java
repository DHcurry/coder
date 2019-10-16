package com.deng.coder.controller;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.User;
import com.deng.coder.service.indexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class indexController {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private indexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        // 获取request中的cookies
        // 验证登录状态
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
        // 调用service中的getList方法获取需要DTO列表
        ArrayList<ArticleListDTO> articleListDTOS = indexService.getList();
        model.addAttribute("articleArrayList",articleListDTOS);
        return "index";
    }

}
