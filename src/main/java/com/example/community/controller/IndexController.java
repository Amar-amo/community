package com.example.community.controller;

import com.example.community.dto.IndexPaginationDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.IndexPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Amar_amo
 * @date 2019/12/5 15:02
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IndexPaginationService indexPaginationService;
    @Value("${pagesize}")
    private int pagesize;
    @GetMapping("/")
    public String Index(HttpServletRequest request,
    @RequestParam(name = "page",defaultValue = "1")int pagenum ) {
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
        //PageHelper.startPage(pagenum, pagesize);
        IndexPaginationDTO indexPaginationDTO = indexPaginationService.list(pagenum,pagesize);
        //PageInfo<PublishDTO> pageInfo = new PageInfo<>(publish_list);
        //String tmp = "/?page";
        //String currentpagenum = String.valueOf(pagenum);
        //currentpagenum=tmp+currentpagenum;
        request.getSession().setAttribute("pagenum",pagenum);
        request.getSession().setAttribute("pagination", indexPaginationDTO);
        return "index";
    }


}
