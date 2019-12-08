package com.example.community.controller;

import com.example.community.dto.IndexPaginationDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.PaginationService;
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
    private PaginationService paginationService;
    @Value("${pagesize}")
    private int pagesize;
    @GetMapping("/")
    public String Index(HttpServletRequest request,
    @RequestParam(name = "page",defaultValue = "1")int pagenum ) {

        IndexPaginationDTO indexPaginationDTO = paginationService.list(pagenum,pagesize);
        request.getSession().setAttribute("pagenum",pagenum);
        request.getSession().setAttribute("pagination", indexPaginationDTO);
        return "index";
    }


}
