package com.deng.coder.controller;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.mapper.CommentMapper;
import com.deng.coder.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
    public String getPage(@PathVariable(name = "articleId") int articleId,
                          Model model){
        // 这里是获取文章内容
        ArticleListDTO articleListDTO = articleService.getArticleInfo(articleId);
        model.addAttribute("article",articleListDTO);
        return "article";
    }

}
