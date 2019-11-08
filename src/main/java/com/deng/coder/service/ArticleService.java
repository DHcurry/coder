package com.deng.coder.service;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.exception.CustomErrorcode;
import com.deng.coder.exception.CustomException;
import com.deng.coder.exception.ICustomErrorcode;
import com.deng.coder.mapper.ArticleExtMapper;
import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.Article;
import com.deng.coder.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleExtMapper articleExtMapper;

    /**
     * 向前台展示文章的数据
     * @param articleId
     * @return
     */
    public ArticleListDTO getArticleInfo(int articleId){
        ArticleListDTO articleListDTO = new ArticleListDTO();
        // 根据文章id寻找文章
        Article article = articleMapper.selectByPrimaryKey(articleId);
        // 如果没有找到文章就要报错
        if(article == null){
            throw new CustomException(CustomErrorcode.QUESTION_NOT_FOUND);
        }

        // 如果文章找到就累积阅读数
        article = countView(article);

        // 通过文章寻找作者
        User user = userMapper.findByInnerId(article.getWriterId());
        // 将内容放进去
        BeanUtils.copyProperties(article,articleListDTO);
        articleListDTO.setUser(user);

        return articleListDTO;
    }

    /**
     * 更新浏览数
     * @param article
     * @return
     */
    public Article countView(Article article){
        article.setViewAccount(article.getViewAccount()+1);
        articleMapper.updateByPrimaryKey(article);
        return article;
    }

}
