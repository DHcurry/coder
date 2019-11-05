package com.deng.coder.service;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.dto.PageShowDTO;
import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.Article;
import com.deng.coder.models.ArticleExample;
import com.deng.coder.models.User;
import org.apache.ibatis.session.RowBounds;
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

    public PageShowDTO  getList(int page, int size) {
        // 创建用于主页展示的对象
        PageShowDTO pageShowDTO = new PageShowDTO();
        // 从数据库中获取最新文章列表并分页展示到前台
        int offset = size*(page - 1);
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("gmt_create desc");
        ArrayList<Article> articleArrayList = (ArrayList<Article>) articleMapper.selectByExampleWithRowbounds(articleExample,new RowBounds(offset,size));
        // 将该数据封装到ArticleListDTO中
        ArrayList<ArticleListDTO> articleListDTOs = new ArrayList<>();
        for(Article article : articleArrayList){
            ArticleListDTO articleListDTO = new ArticleListDTO();
            articleListDTO.setId(article.getId());
            articleListDTO.setTitle(article.getTitle());
            articleListDTO.setTag(article.getTag());
            articleListDTO.setContent(article.getContent());
            articleListDTO.setGmtCreate(article.getGmtCreate());
            articleListDTO.setViewAccount(article.getViewAccount());
            articleListDTO.setLikeAccount(article.getLikeAccount());
            articleListDTO.setCommentAccount(article.getCommentAccount());
            User user = userMapper.findByInnerId(article.getWriterId());
            articleListDTO.setUser(user);
            articleListDTOs.add(articleListDTO);
        }
        // 将该页文章列表放入到该模块中
        pageShowDTO.setArticleListDTOS(articleListDTOs);
        // 依据页面page，文章数量，size决定pageShowDTO的属性
        pageShowDTO.pageManage(page,size,(int)articleMapper.countByExample(articleExample));
        return pageShowDTO;
    }
}
