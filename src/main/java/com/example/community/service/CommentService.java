package com.example.community.service;

import com.example.community.dto.CommentDTO;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 21:21
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    public List<CommentDTO> findByPageId(long id) {
        List<Comment> commentList = commentMapper.findByPageId(id);
        List<CommentDTO> commentDTOList=new ArrayList<>();
        for (Comment comment:commentList) {
            User user = userMapper.findById(comment.getCommentator());
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setAvatar_url(user.getAvatar_url());
            commentDTO.setName(user.getName());
            commentDTO.setBio(user.getBio());
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
