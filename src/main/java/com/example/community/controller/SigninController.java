package com.example.community.controller;

import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Amar_amo
 * @date 2019/12/7 15:37
 */
@Controller
public class SigninController {
    @Value("${Hash.salt}")
    private String salt;
    @Value("${Hash.times}")
    private int times;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/signin")
    public String Signin() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signin(HttpServletRequest request,
                         HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);
        System.out.println(password);
        User get_user = userMapper.findByEmail(email);
        if (get_user == null) {
            System.out.println("GG");
            return "redirect:signin";
        }
        System.out.println("111" + get_user);
        String hash_password = new Sha256Hash(password, salt, times).toString();
        System.out.println(hash_password);
        System.out.println(get_user.getPassword());
        if (hash_password.equals(get_user.getPassword())) {
            request.getSession().setAttribute("user", get_user);
            String token = UUID.randomUUID().toString();
            response.addCookie(new Cookie("token", token));
            userMapper.updateByEmail(email,token);
            return "redirect:/";
        } else {
            return "redirect:signin";
        }

    }
}
