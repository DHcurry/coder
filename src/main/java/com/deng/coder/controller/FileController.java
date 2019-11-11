package com.deng.coder.controller;

import com.deng.coder.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FileController {

    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess((byte) 1);
        String url = "/static/images/";
        fileDTO.setUrl("/static/images/think.jpg");
        return fileDTO;
    }
}
