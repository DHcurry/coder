package com.deng.coder.service;

import com.deng.coder.dto.CommentDTO;
import com.deng.coder.mapper.CommentMapper;
import com.deng.coder.models.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public void add(CommentDTO commentDTO){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(System.currentTimeMillis());
        commentMapper.insert(comment);
    }
}
