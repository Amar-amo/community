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
import java.sql.Date;

@Controller
public class UserDataUpdateController
{
    @Autowired
    private UserMapper userMapper;

   @PostMapping("/profile/userdata")
    public String updatedata(HttpServletRequest request) {

       User user = (User) request.getSession().getAttribute("user");
       String email = request.getParameter("email");
//String password = request.getParameter("password");
       String name = request.getParameter("name");
       Date modified_time = new Date(System.currentTimeMillis());
       System.out.println(email);
//System.out.println(password);
       System.out.println(name);

       System.out.println(user);


       /*zai Mapper xieyige updateById  参数为（id,name...,email...,password...,之类的）*/
       userMapper.updateById(name,email,modified_time,user.getId());


       return "redirect:userdata";

    }
}
