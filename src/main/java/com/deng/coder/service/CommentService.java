package com.deng.coder.service;

import com.deng.coder.dto.CommentDTO;
import com.deng.coder.dto.CommentShowDTO;
import com.deng.coder.enumrate.NotifyEnum;
import com.deng.coder.exception.CustomErrorcode;
import com.deng.coder.exception.CustomException;
import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.mapper.CommentMapper;
import com.deng.coder.mapper.NotifyMapper;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private NotifyMapper notifyMapper;

    /**
     * 向评论表中添加数据保存
     * @param commentDTO
     */
    public void add(CommentDTO commentDTO, User user){
        // 判定内容是否为空
        if(commentDTO.getContent().equals("")){
            throw new CustomException(CustomErrorcode.REPEAT_NOT_FOUND);
        }
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setType((byte) commentDTO.getType());
        comment.setArticleId(commentDTO.getArticleId());
        comment.setCommentor(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(System.currentTimeMillis());
        commentMapper.insert(comment);

        //添加评论以后要给用户通知
        addNotify(commentDTO,user);

    }

    /**
     * 将该文章下的评论通通寻找到并返回
     * @return
     */
    public ArrayList<CommentShowDTO> show(int articleId){
        // commentShowDTOS 是封装显示到前端的内容
        ArrayList<CommentShowDTO> commentShowDTOS = new ArrayList<>();

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andArticleIdEqualTo(articleId);
        commentExample.setOrderByClause("gmt_create desc");
        ArrayList<Comment> comments = new ArrayList<>();
        comments = (ArrayList<Comment>) commentMapper.selectByExample(commentExample);
        // 遍历评论，填补commentshowdto
        HashMap<Integer , User> userMap = new HashMap<>();
        for (Comment comment: comments
             ) {
            CommentShowDTO commentShowDTO  = new CommentShowDTO();
            commentShowDTO.setContent(comment.getContent());
            commentShowDTO.setGmtCreate(comment.getGmtCreate());
            // 我们想在这里进行优化：不能每次都使用数据库寻找用户，而是将user放内存中匹配这个用户是否
            User user = null;
            // 如果usermap中存在用户id，那么就直接将其值写入
            if(userMap.containsKey(comment.getCommentor())){
                user = userMap.get(comment.getCommentor());
            } else{
                user = userMapper.findByInnerId(comment.getCommentor());
                userMap.put(user.getId(),user);
            }
            commentShowDTO.setUser(user);
            commentShowDTOS.add(commentShowDTO);
        }
        return commentShowDTOS;
    }
    void addNotify(CommentDTO commentDTO,User user){
        Notify notify = new Notify();
        notify.setGmtCreate(System.currentTimeMillis());
        // 根据文章id寻找创作者
        Article article = articleMapper.selectByPrimaryKey(commentDTO.getArticleId());
        articleMapper.selectByPrimaryKey(commentDTO.getArticleId());
        notify.setReceiver(article.getWriterId());

        notify.setWriterId(user.getId());
        notify.setStatus((byte) 1);
        notify.setType(NotifyEnum.NOTIFY_COMMENT.getId());
        notifyMapper.add(notify);
    }
}
