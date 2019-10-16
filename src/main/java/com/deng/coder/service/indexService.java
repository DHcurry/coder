package com.deng.coder.service;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.Article;
import com.deng.coder.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class indexService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    public ArrayList<ArticleListDTO> getList() {
        // 从数据库中获取最新文章列表展示到前台
        ArrayList<Article> articleArrayList = articleMapper.findByTime();
        // 将该数据封装到ArticleListDTO中
        ArrayList<ArticleListDTO> articleListDTOs = new ArrayList<>();
        for(Article article : articleArrayList){
            ArticleListDTO articleListDTO = new ArticleListDTO();
            BeanUtils.copyProperties(article,articleListDTO);
            User user = userMapper.findByInnerId(article.getWriterId());
            articleListDTO.setUser(user);
            articleListDTOs.add(articleListDTO);
        }
        return articleListDTOs;
    }
}
