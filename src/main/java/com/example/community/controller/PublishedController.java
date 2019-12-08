package com.example.community.controller;

import com.example.community.dto.PublishDTO;
import com.example.community.service.PublishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amar_amo
 * @date 2019/12/8 15:19
 */
@Controller
public class PublishedController {
    @Autowired
    private PublishedService publishedService;

    @GetMapping("/published/{id}")
    public String Published(@PathVariable(name = "id") long id,
                            HttpServletRequest request) {
        PublishDTO publishDTO = publishedService.getById(id);
        request.getSession().setAttribute("artical",publishDTO);
        return "published";
    }
}
