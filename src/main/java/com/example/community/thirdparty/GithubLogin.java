package com.example.community.thirdparty;

import com.alibaba.fastjson.JSON;
import com.example.community.dto.GithubAccessTokenDTO;
import com.example.community.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Amar_amo
 * @date 2019/12/5 18:19
 */
@Component
public class GithubLogin {
    public String getAccessToken(GithubAccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
           String back= response.body().string();
            System.out.println(back);
            return back;
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }

    public GithubUserDTO getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?"+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //System.out.println(string);
            GithubUserDTO githubUserDTO = JSON.parseObject(string, GithubUserDTO.class);
            return githubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }
}
