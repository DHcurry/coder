package com.deng.coder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class publishController {

    @RequestMapping("/publish")
    String publish(){
        return "publish";
    }
}
