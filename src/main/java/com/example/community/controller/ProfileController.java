package com.example.community.controller;

import com.example.community.dto.IndexPaginationDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.IndexPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Amar_amo
 * @date 2019/12/8 12:48
 */
@Controller
public class ProfileController {
    @Value("${pagesize}")
    private int pagesize;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IndexPaginationService indexPaginationService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String name,
                          @RequestParam(name = "page",defaultValue = "1")int pagenum ,
                          HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        System.out.println("验证成功");
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
       User user = (User) request.getSession().getAttribute("user");
       long id = user.getId();
        System.out.println(id);
        if ("published".equals(name))
        {
            //request.getSession().setAttribute("setion",name);
            request.getSession().setAttribute("setionName","我的发布");
        }

        IndexPaginationDTO indexPaginationDTO = indexPaginationService.list(pagenum,pagesize,id);
        request.getSession().setAttribute("pagenum",pagenum);
        request.getSession().setAttribute("pagination", indexPaginationDTO);
        return "profile";
    }
}
