package com.deng.coder.controller;

import com.deng.coder.dto.AccessTokenDTO;
import com.deng.coder.dto.GithubUser;
import com.deng.coder.provider.GithubProvier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvier githubProvier;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.client.uri}")
    private String redirect_uri;

    @RequestMapping("/callback")
    public String Callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("ab0c39aa32fa1a8aa1a5");
        accessTokenDTO.setClient_secret("ec1bcecf3eb0b7a7c843ffa0f70e5ec529a1be7e");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");

        String access_token = githubProvier.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvier.getUser(access_token);
        System.out.println(user.getName());

        return "index";
    }
}
