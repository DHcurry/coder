package com.deng.coder.service;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.dto.PageShowDTO;
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

    public PageShowDTO getList(int page, int size) {
        // 创建用于主页展示的对象
        PageShowDTO pageShowDTO = new PageShowDTO();
        // 从数据库中获取最新文章列表并分页展示到前台
        int offset = size*(page - 1);
        ArrayList<Article> articleArrayList = articleMapper.findByTime(offset,size);
        // 将该数据封装到ArticleListDTO中
        ArrayList<ArticleListDTO> articleListDTOs = new ArrayList<>();
        for(Article article : articleArrayList){
            ArticleListDTO articleListDTO = new ArticleListDTO();
            BeanUtils.copyProperties(article,articleListDTO);
            User user = userMapper.findByInnerId(article.getWriterId());
            articleListDTO.setUser(user);
            articleListDTOs.add(articleListDTO);
        }
        // 将该页文章列表放入到该模块中
        pageShowDTO.setArticleListDTOS(articleListDTOs);
        // 依据页面page，文章数量，size决定pageShowDTO的属性
        pageShowDTO.pageManage(page,size,articleMapper.totalCount());
        return pageShowDTO;
    }
}
