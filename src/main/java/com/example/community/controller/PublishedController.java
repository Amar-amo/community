package com.example.community.controller;

import com.example.community.dto.CommentDTO;
import com.example.community.dto.PublishDTO;
import com.example.community.mapper.PublishMapper;
import com.example.community.model.Publish;
import com.example.community.service.CommentService;
import com.example.community.service.PublishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 15:19
 */
@Controller
public class PublishedController {
    @Autowired
    private PublishedService publishedService;
    @Autowired
    private PublishMapper publishMapper;
    @Autowired
    private CommentService commentService;

    @GetMapping("/published/{id}")
    public String Published(@PathVariable(name = "id") long id,
                            HttpServletRequest request) {

        PublishDTO publishDTO = publishedService.getById(id);
        Publish publish = publishMapper.getById(id);

        request.getSession().setAttribute("artical", publishDTO);
        if (request.getSession().getAttribute("user") != null) {
            System.out.println(publish.getView_count());
            publish.setView_count(publish.getView_count()+1);
            System.out.println(publish.getView_count());
            publishMapper.updateById(publish);
        }

        List<CommentDTO> commentDTOList =commentService.findByPageId(publish.getId());
        request.getSession().setAttribute("comment",commentDTOList);
        return "published";
    }
}
