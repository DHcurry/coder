package com.deng.coder.mapper;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.models.Article;

import java.util.ArrayList;

public interface ArticleExtMapper {
    ArrayList<Article> selectRelated(ArticleListDTO articleListDTO);
}
