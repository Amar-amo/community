package com.example.community.controller;

import com.example.community.dto.PublishDTO;
import com.example.community.mapper.PublishMapper;
import com.example.community.model.Publish;
import com.example.community.model.User;
import com.example.community.service.PublishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private PublishedService publishedService;

    @GetMapping("/publish")
    public String Publish(HttpServletRequest request) {
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
            request.getSession().setAttribute("publish", publish);
            request.getSession().setAttribute("url", "publish");
            return "redirect:error";

        }
        publishMapper.Insert(publish);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String Edit(@PathVariable(name = "id") long id,
                       HttpServletRequest request) {
        PublishDTO publishDTO = publishedService.getById(id);
        request.setAttribute("artical", publishDTO);
        return "publish";
    }

    @PostMapping("/publish/{id}")
    public String Edit(
                       HttpServletRequest request) {
        System.out.println("修改");
        PublishDTO artical = (PublishDTO) request.getSession().getAttribute("artical");
        if (artical==null){
            return "redirect:error";
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String tag = request.getParameter("tag");

        System.out.println(title+"  "+description+"  "+tag);
        Publish publish = new Publish();
        publish.setId(artical.getId());
        publish.setTitle(title);
        publish.setDescription(description);
        publish.setCreator(artical.getCreator());
        publish.setModified_time(new Date(System.currentTimeMillis()));
        publish.setTag(tag);
        publishMapper.updateById(publish);
        return "redirect:/";
    }
}