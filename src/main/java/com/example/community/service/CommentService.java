package com.example.community.service;

import com.example.community.dto.CommentDTO;
import com.example.community.dto.CommentPaginationDTO;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 21:21
 */
@Service
public class CommentService {
    @Value("${pagesize}")
    private int pagesize;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    public CommentPaginationDTO findByPageId(long id, int pagenum) {
        PageHelper.startPage(pagenum, pagesize);
        List<Comment> commentList = commentMapper.findByPageId(id);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            User user = userMapper.findById(comment.getCommentator());
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }

        CommentPaginationDTO commentPaginationDTO = new CommentPaginationDTO();
        commentPaginationDTO.setCommentDTOList(commentDTOList);
        commentPaginationDTO.setFirstPage(pageInfo.isIsFirstPage());
        commentPaginationDTO.setLastPage(pageInfo.isIsLastPage());
        commentPaginationDTO.setNums(pageInfo.getNavigatepageNums());
        commentPaginationDTO.setPagenum(pageInfo.getPageNum());
        commentPaginationDTO.setLastpage(pageInfo.getPages());
        commentPaginationDTO.setTotal(pageInfo.getTotal());
        return  commentPaginationDTO;
    }
}
