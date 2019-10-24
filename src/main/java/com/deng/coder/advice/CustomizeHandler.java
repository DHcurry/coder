package com.deng.coder.advice;

import com.deng.coder.exception.CustomException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeHandler{
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Model model,Throwable e){
        if(e instanceof CustomException){
            model.addAttribute("message",e.getMessage());
        }else{
            model.addAttribute("message","服务器未知异常");
        }
        return new ModelAndView("error");
    }
}
