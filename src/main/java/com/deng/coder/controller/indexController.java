package com.deng.coder.controller;

import com.deng.coder.dto.PageShowDTO;
import com.deng.coder.service.indexService;
import com.deng.coder.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class indexController {
    @Autowired
    private loginService loginService;

    @Autowired
    private indexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") int page,
                        @RequestParam(name = "size",defaultValue = "5") int size){
        // 验证登录状态

        // 调用service中的getList方法获取需要DTO列表
        PageShowDTO pageShowDTO = indexService.getList(page, size);
        model.addAttribute("pageShowDTO",pageShowDTO);
        return "index";
    }

}
