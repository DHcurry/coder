package com.deng.coder.controller;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.dto.CommentDTO;
import com.deng.coder.dto.CommentShowDTO;
import com.deng.coder.mapper.CommentMapper;
import com.deng.coder.models.Article;
import com.deng.coder.service.ArticleService;
import com.deng.coder.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{articleId}")
    public String getPage(@PathVariable(name = "articleId") int articleId,
                          Model model){
        // 这里是获取文章内容
        ArticleListDTO articleListDTO = articleService.getArticleInfo(articleId);
        model.addAttribute("article",articleListDTO);

        // 获取该文章所有的评论
        ArrayList<CommentShowDTO> commentShowDTOS = new ArrayList<>();
        commentShowDTOS = commentService.show(articleId);
        model.addAttribute("commentShowDTOS",commentShowDTOS);

        // 获取相关问题列表
        ArrayList<ArticleListDTO> articles = null;
        articles = articleService.findRelated(articleListDTO);
        model.addAttribute("articleDTO",articles);
        return "article";
    }

}
