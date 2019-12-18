package com.example.community.controller;

import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * @author Amar_amo
 * @date 2019/12/7 15:24
 */
@Controller
public class SignupController {
    @Value("${Hash.salt}")
    private String salt;
    @Value("${Hash.times}")
    private int times;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/signup")
    public String Signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String Signup(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        User user = new User();
        //System.out.println(user.getName());
        System.out.println(email);
        System.out.println(password);
        System.out.println(name);
        String hash_password = new Sha256Hash(password, salt, times).toString();
        System.out.println(hash_password);
        user.setEmail(email);
        user.setPassword(hash_password);
        user.setName(name);
        user.setCreate_time(new Date(System.currentTimeMillis()));
        user.setModified_time(user.getCreate_time());
        userMapper.Insert(user);

        return "redirect:signin";

    }
}
