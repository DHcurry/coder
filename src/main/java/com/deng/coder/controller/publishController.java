package com.deng.coder.controller;

import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.Article;
import com.deng.coder.models.User;
import com.deng.coder.service.loginService;
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
    private loginService loginService;

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(value = "/publish",method = RequestMethod.GET)
    String publish(HttpServletRequest request,
                   Model model) {
        // 验证登录状态已经通过拦截器自动做了

        return "publish";
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public String doPublic(@Param("title") String title,
                           @Param("content") String content,
                           @Param("tag") String tag,
                           HttpServletRequest request){
        // 验证登录
        User user = (User) request.getSession().getAttribute("user");

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setTag(tag);
        article.setGmtCreate(System.currentTimeMillis());
        article.setGmtModify(System.currentTimeMillis());
        article.setWriterId(user.getId());
        article.setViewAccount(0);
        article.setLikeAccount(0);
        article.setCommentAccount(0);
        articleMapper.insert(article);
        return "publish";
    }
}
