package com.deng.coder.provider;

import com.alibaba.fastjson.JSON;
import com.deng.coder.dto.AccessTokenDTO;
import com.deng.coder.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {


    /**
     * 从GitHub中通过携带AccesTokenDTO中的信息获取token
     * @param accessTokenDTO
     * @return String access_token
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token"  )
                .post(body)
                .build();
        String access_token = null;
        try (Response response = client.newCall(request).execute()) {
            access_token =  response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        String[] splitStr = access_token.split("&");
        String tokenstr = splitStr[0];
        return tokenstr.split("=")[1];
    }

    /**
     * 通过token去GitHub上拿到用户的信息,并封装到GithubUser类中
     * @param access_token
     * @return GithubUser
     */
    public GithubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+access_token)
                    .build();
        GithubUser user = null;
        try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                user= JSON.parseObject(string,GithubUser.class);
            }catch (IOException e){
                e.printStackTrace();
            }
        return user;
    }
}
