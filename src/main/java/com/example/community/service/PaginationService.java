package com.example.community.service;

import com.example.community.dto.IndexPaginationDTO;
import com.example.community.dto.LastCommentDTO;
import com.example.community.dto.LastestCommentPaginationDTO;
import com.example.community.dto.PublishDTO;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.PublishMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.Publish;
import com.example.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/7 23:41
 */
@Service
public class PaginationService
{
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishMapper publishMapper;

    public IndexPaginationDTO list(int pagenum, int pagesize)
    {
        PageHelper.startPage(pagenum, pagesize);
        List<Publish> publishList = publishMapper.list();
        return getIndexPaginationDTO(publishList, userMapper);
    }

    public IndexPaginationDTO list(int pagenum, int pagesize, long id)
    {
        PageHelper.startPage(pagenum, pagesize);
        List<Publish> publishList = publishMapper.getListById(id);
        return getIndexPaginationDTO(publishList, userMapper);
    }

    public IndexPaginationDTO list(int pagenum, int pagesize, String keyword)
    {
        PageHelper.startPage(pagenum, pagesize);
        keyword = "%" + keyword + "%";
        List<Publish> publishList = publishMapper.getByKyeword(keyword);
        return getIndexPaginationDTO(publishList, userMapper);
    }
    public LastestCommentPaginationDTO Publishlist(int pagenum, int pagesize, long id)
    {
        List<Publish> publishList = publishMapper.getListById(id);
        List<Comment> commentList = new ArrayList<>();
        List<LastCommentDTO> lastCommentDTOList = new ArrayList<>();
        List<LastCommentDTO> templastCommentDTOList = new ArrayList<>();

        for (Publish publish : publishList)
        {
            List<Comment> comments = new ArrayList<>();
            comments = commentMapper.findByPageIdWithOrderBydesc(publish.getId());
            commentList.addAll(comments);
        }
        for (Comment comment:commentList)
        {
            LastCommentDTO lastCommentDTO = new LastCommentDTO();
            User byId = userMapper.findById(comment.getCommentator());
            lastCommentDTO.setComment(comment);
            lastCommentDTO.setUser(byId);
            lastCommentDTOList.add(lastCommentDTO);
        }

        PageHelper.startPage(pagenum, pagesize);
        //lastCommentDTOList.addAll(templastCommentDTOList);
        PageInfo<LastCommentDTO> pageInfo = new PageInfo< >(lastCommentDTOList);
        LastestCommentPaginationDTO lastestCommentPaginationDTO = new LastestCommentPaginationDTO();
        lastestCommentPaginationDTO.setLastCommentDTOList(lastCommentDTOList);
        lastestCommentPaginationDTO.setFirstPage(pageInfo.isIsFirstPage());
        lastestCommentPaginationDTO.setLastPage(pageInfo.isIsLastPage());
        lastestCommentPaginationDTO.setNums(pageInfo.getNavigatepageNums());
        lastestCommentPaginationDTO.setPagenum(pageInfo.getPageNum());
        lastestCommentPaginationDTO.setLastpage(pageInfo.getPages());
        return lastestCommentPaginationDTO;
    }



    static IndexPaginationDTO getIndexPaginationDTO(List<Publish> publishList, UserMapper userMapper)
    {
        PageInfo<Publish> pageInfo = new PageInfo<>(publishList);
        List<PublishDTO> publishDTOList = new ArrayList<>();
        for (Publish publish : publishList)
        {
            User user = userMapper.findById(publish.getCreator());
            PublishDTO publishDTO = new PublishDTO();
            BeanUtils.copyProperties(publish, publishDTO);
            publishDTO.setUser(user);
            publishDTOList.add(publishDTO);
        }
        IndexPaginationDTO indexPaginationDTO = new IndexPaginationDTO();
        indexPaginationDTO.setPublishDTOList(publishDTOList);
        indexPaginationDTO.setFirstPage(pageInfo.isIsFirstPage());
        indexPaginationDTO.setLastPage(pageInfo.isIsLastPage());
        indexPaginationDTO.setNums(pageInfo.getNavigatepageNums());
        indexPaginationDTO.setPagenum(pageInfo.getPageNum());
        indexPaginationDTO.setLastpage(pageInfo.getPages());
        return indexPaginationDTO;
    }
}
