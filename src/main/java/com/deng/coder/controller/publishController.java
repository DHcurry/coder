package com.deng.coder.controller;

import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.Article;
import com.deng.coder.models.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
class publishController {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(value = "/publish",method = RequestMethod.GET)
    String publish(HttpServletRequest request,
                   Model model) {
        // 验证登录状态
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = mapper.findByToken(token);
                }
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                } else {
                    // 如果为空时，要报错误信息
                    request.getSession().setAttribute("user", null);
                    model.addAttribute("error", "用户未登录");
                }

            }
        }
        return "publish";
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public String doPublic(@Param("title") String title,
                           @Param("content") String content,
                           @Param("tag") String tag,
                           HttpServletRequest request){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = mapper.findByToken(token);
                } else
                    continue;
            }
        }

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setTag(tag);
        article.setGmtCreate(System.currentTimeMillis());
        article.setGmtModify(System.currentTimeMillis());
        article.setWriterId(user.getId());
        articleMapper.add(article);
        return "publish";
    }
}
