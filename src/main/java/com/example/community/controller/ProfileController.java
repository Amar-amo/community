package com.example.community.controller;

import com.example.community.dto.IndexPaginationDTO;
import com.example.community.dto.LastestCommentPaginationDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.CommentService;
import com.example.community.service.PaginationService;
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
public class ProfileController
{
    @Value("${pagesize}")
    private int pagesize;
    @Autowired
    private UserDataUpdateController userDataUpdateController;
    @Autowired
    private PaginationService paginationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String name,
                          @RequestParam(name = "page", defaultValue = "1") int pagenum,
                          HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");

        long id = user.getId();
        System.out.println(id);
        if ("published".equals(name))
        {
            request.getSession().setAttribute("setionName", "我的发布");
            IndexPaginationDTO indexPaginationDTO = paginationService.list(pagenum, pagesize, id);
            request.getSession().setAttribute("pagenum", pagenum);
            request.getSession().setAttribute("pagination", indexPaginationDTO);
        } else if ("userdata".equals(name))
        {
            System.out.println("tttt");
            request.getSession().setAttribute("setionName", "个人资料");
            return "profile2";

        }
        else if ("upavatar".equals(name))
        {
            System.out.println("tttt");
            request.getSession().setAttribute("setionName", "个人资料");
            return "profile2";

        }else if ("lastestcomment".equals(name))
        {
            request.getSession().setAttribute("setionName", "最新回复");
            LastestCommentPaginationDTO lastestCommentPaginationDTO = paginationService.Publishlist(pagenum, pagesize, id);
            request.getSession().setAttribute("pagenum", pagenum);
            request.getSession().setAttribute("lastestcomment", lastestCommentPaginationDTO);
            return "gainedcomment";
        }

        return "profile";
    }
}
