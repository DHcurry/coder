package com.deng.coder.controller;

import com.deng.coder.dto.CommentDTO;
import com.deng.coder.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 利用该注解，可以将JavaBean直接转成json返回给前台
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object doPost(@RequestBody CommentDTO commentDTO){
        commentService.add(commentDTO);
        return "success";
    }
}
