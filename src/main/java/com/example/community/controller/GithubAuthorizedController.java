package com.example.community.controller;

import com.example.community.dto.GithubAccessTokenDTO;
import com.example.community.dto.GithubUserDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.thirdparty.GithubLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.UUID;

/**
 * @author Amar_amo
 * @date 2019/12/5 18:08
 */
@Controller
public class GithubAuthorizedController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GithubLogin github_login;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secrect}")
    private String client_secrect;
    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        GithubAccessTokenDTO accesstokendto = new GithubAccessTokenDTO();
        accesstokendto.setClient_id(client_id);
        accesstokendto.setClient_secret(client_secrect);
        accesstokendto.setRedirect_uri(redirect_uri);
        accesstokendto.setCode(code);
        accesstokendto.setState(state);
        String accessToken = github_login.getAccessToken(accesstokendto);
        GithubUserDTO github_user = github_login.getUser(accessToken);
        System.out.println(github_user.getName());
        System.out.println(github_user.getId());
        System.out.println(github_user.getBio());
        if (github_user != null) {
            User user = new User();
            user.setEmail(github_user.getEmail());
            user.setName(github_user.getName());
            user.setAccount_id(String.valueOf(github_user.getId()));
            user.setBio(github_user.getBio());
            user.setAvatar_url(github_user.getAvatar_url());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setCreate_time(new Date(System.currentTimeMillis()));
            user.setModified_time(user.getCreate_time());
            User user1 = userMapper.findByAccountId(user.getAccount_id());
            if (user1!=null)
            {
                response.addCookie(new Cookie("token", token));
                userMapper.updateByAccountId(user.getAccount_id(), token);
                return "redirect:/";
            }
            // userMapper.Insert(user);
            //不为空即为登录成功，写cookie
            response.addCookie(new Cookie("token", token));
            //利用session传参
            request.getSession().setAttribute("user", user);
            return "redirect:signup1";
        } else {
            return "redirect:signin";
        }
    }

}
