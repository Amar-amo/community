package com.example.community.controller;

import com.example.community.dto.PublishDTO;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.PublishMapper;
import com.example.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 20:39
 */
@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PublishMapper publishMapper;
    @PostMapping("/comment")
    public String Comment(HttpServletRequest request) {
        String content = request.getParameter("content");
        PublishDTO publishDTO = (PublishDTO) request.getSession().getAttribute("artical");
        Comment comment = new Comment();
        comment.setCommentator(publishDTO.getUser().getId());
        comment.setContent(content);
        comment.setCreate_time(new Timestamp(System.currentTimeMillis()));
        comment.setModified_time(comment.getCreate_time());
        comment.setPage_id(publishDTO.getId());
        commentMapper.Insert(comment);
        System.out.println(publishDTO.getUser().getId());
        List<Comment> comments = commentMapper.findByPageId(publishDTO.getId());
        System.out.println(comments.size());
        publishMapper.updateCommenCountById(comments.size(),publishDTO.getId());
        return "redirect:/published/"+publishDTO.getId();
    }
}
