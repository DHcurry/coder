package com.deng.coder.controller;

import com.deng.coder.dto.AccessTokenDTO;
import com.deng.coder.dto.GithubUser;
import com.deng.coder.mapper.UserMapper;
import com.deng.coder.models.User;
import com.deng.coder.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper mapper;

    @RequestMapping("/callback")
    public String Callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);

        String access_token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(access_token);


        if(user != null){
            // 获取到user,登录成功,写入session
            // 获取用户,并将信息封装到数据库中
            request.getSession().setAttribute("user",user);
            // GitHub上获取的信息只是一部分,我们还需要其他的信息,而这个User才是我们用在数据库中的表
            User store_user = new User();
            store_user.setAccountId(String.valueOf(user.getId()));
            store_user.setName(user.getName());
            store_user.setToken(UUID.randomUUID().toString());
            store_user.setGmtCreate(System.currentTimeMillis());
            store_user.setGmtModify(System.currentTimeMillis());
            // 使用mybatis运行插入代码
            mapper.add(store_user);
        }
        else{
            // 登录失败,重新登录
        }

        return "index";
    }
}
