package com.deng.coder.service;

import com.deng.coder.dto.NotifyDTO;
import com.deng.coder.enumrate.NotifyEnum;
import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.mapper.NotifyMapper;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.Article;
import com.deng.coder.models.Notify;
import com.deng.coder.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class notifyService {

    @Autowired
    private NotifyMapper notifyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    public ArrayList<NotifyDTO> getUnreadList() {
        ArrayList<Notify> notifies = notifyMapper.getUnreadList();
        ArrayList<NotifyDTO> notifyDTOS = new ArrayList<>();
        for (Notify notify: notifies
             ) {
            NotifyDTO notifyDTO = new NotifyDTO();
            // 获取用户信息
            User user = userMapper.findByInnerId(notify.getWriterId());
            notifyDTO.setWriter(user.getName());
            // 设置回复类型
            notifyDTO.setType(NotifyEnum.NOTIFY_COMMENT.getTag());
            // 设置回复的文章标题
            Article article = articleMapper.selectByPrimaryKey(notify.getArticleId());
            notifyDTO.setArticleId(article.getId());
            notifyDTO.setArticle(article.getTitle());
            notifyDTOS.add(notifyDTO);
        }
        return notifyDTOS;
    }

    public void cancel(int articleId) {
        notifyMapper.cancel(articleId);
    }
}
