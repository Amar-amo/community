package com.example.community.controller;


import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Controller
public class UserAvatarController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/profile/upavatar")
    public String updateavatar() {
        return "profile2";
    }

    @PostMapping("/profile/upavatar")
    public String updateavatar(HttpServletRequest request) {


        User user = (User) request.getSession().getAttribute("user");
        String avatar_name = request.getParameter("avatar_name");
        //String password = request.getParameter("password");
        String avatar_url = "/img/"+avatar_name;
        System.out.println(avatar_url);

        Date modified_time = new Date(System.currentTimeMillis());

        /*zai Mapper xieyige updateById  参数为（id,name...,email...,password...,之类的）*/
        userMapper.updateavatarById(avatar_url,modified_time,user.getId());
        return "redirect:http://localhost:8080/profile/userdata";
    }
}
