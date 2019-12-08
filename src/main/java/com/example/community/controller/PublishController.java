package com.example.community.controller;

import com.example.community.mapper.PublishMapper;
import com.example.community.model.Publish;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * @author Amar_amo
 * @date 2019/12/7 18:40
 */
@Controller
public class PublishController {
    @Autowired
    private PublishMapper publishMapper;

    @GetMapping("/publish")
    public String Publish(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        request.getSession().setAttribute("user",user);
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String tag = request.getParameter("tag");

        Publish publish = new Publish();
        publish.setTitle(title);
        publish.setDescription(description);
        publish.setCreator(user.getId());
        publish.setCreate_time(new Date(System.currentTimeMillis()));
        publish.setModified_time(publish.getCreate_time());
        publish.setTag(tag);
        if (user == null) {
            request.getSession().setAttribute("publish",publish);
            request.getSession().setAttribute("url","publish");
            return "redirect:error";

        }
        publishMapper.Insert(publish);
        return "redirect:/";
    }
}