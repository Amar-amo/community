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

/**
 * @author Amar_amo
 * @date 2019/12/7 13:50
 */
@Controller
public class ThirdPartySignupController {

    @Value("${Hash.salt}")
    private String salt;
    @Value("${Hash.times}")
    private int times;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/signup1")
    public String Signup1() {
        return "signup1";
    }

    @PostMapping("/signup1")
    public String ThirdPartySignup(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getName());
        System.out.println(email);
        System.out.println(password);
        String hash_password = new Sha256Hash(password, salt, times).toString();
        if (name != null) {
            user.setName(name);
        }
        user.setEmail(email);
        user.setPassword(hash_password);
        userMapper.Insert(user);
        return "redirect:/";

    }
}
