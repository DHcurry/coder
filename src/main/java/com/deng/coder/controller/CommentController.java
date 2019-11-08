package com.deng.coder.controller;

import com.deng.coder.dto.CommentDTO;
import com.deng.coder.models.User;
import com.deng.coder.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 增加该注解可以提供事务的支持
    @Transactional
    // 利用该注解，可以将JavaBean直接转成json返回给前台
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object doPost(@RequestBody CommentDTO commentDTO,
                         HttpServletRequest request) {
        // 判断当前用户是否登录，没有登录就不显示回复框(这部分已经在前端做了)
        // 判断回复是否为空，为空则报出异常
        // 这个异常判定放在service中
        User user = (User)request.getSession().getAttribute("user");
        commentService.add(commentDTO,user);

        return "success";
    }
}
