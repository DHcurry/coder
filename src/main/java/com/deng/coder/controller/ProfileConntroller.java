package com.deng.coder.controller;

import com.deng.coder.dto.ArticleListDTO;
import com.deng.coder.dto.NotifyDTO;
import com.deng.coder.dto.PageShowDTO;
import com.deng.coder.mapper.ArticleMapper;
import com.deng.coder.models.Article;
import com.deng.coder.models.User;
import com.deng.coder.service.loginService;
import com.deng.coder.service.notifyService;
import com.deng.coder.service.profileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ProfileConntroller {
    @Autowired
    private loginService loginService;

    @Autowired
    private profileService profileService;

    @Autowired
    private notifyService notifyService;

    @GetMapping("/profile")
    public String getProfile(HttpServletRequest request,
                             @RequestParam(value = "chick",defaultValue = "myarticle") String chick,
                             @RequestParam(value = "page",defaultValue = "1")int page,
                             @RequestParam(value = "size",defaultValue = "5")int size,
                             Model model){

        // 验证登录
        User user = (User)request.getSession().getAttribute("user");
        // 判断用户点击的列表
        if(chick.equals("myarticle")){
            // 根据用户信息获取该用户文章列表
            PageShowDTO pageShowDTO = profileService.getList(user,page,size);
            model.addAttribute("pageShowDTO",pageShowDTO);
            model.addAttribute("select","article");
        }
        if(chick.equals("notify")){
            ArrayList<NotifyDTO> notifyDTOS = notifyService.getUnreadList();
            model.addAttribute("select","notify");
            model.addAttribute("notifyDTO",notifyDTOS);
        }
        return "profile";
    }
}
