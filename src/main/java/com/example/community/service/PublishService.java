package com.example.community.service;

import com.example.community.dto.PaginationDTO;
import com.example.community.dto.PublishDTO;
import com.example.community.mapper.PublishMapper;
import com.example.community.mapper.UserMapper;
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
public class PublishService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishMapper publishMapper;

    public PaginationDTO list(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        List<Publish> publishList =publishMapper.list();
        PageInfo<Publish> pageInfo = new PageInfo<>(publishList);
        List<PublishDTO> publishDTOList=new ArrayList<>();
        for (Publish publish:publishList) {
            User user =userMapper.findById(publish.getCreator());
            PublishDTO publishDTO = new PublishDTO();
            BeanUtils.copyProperties(publish,publishDTO);
            publishDTO.setUser(user);
            publishDTOList.add(publishDTO);
        }
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPublishDTOList(publishDTOList);
        paginationDTO.setFirstPage(pageInfo.isIsFirstPage());
        paginationDTO.setLastPage(pageInfo.isIsLastPage());
        paginationDTO.setNums(pageInfo.getNavigatepageNums());
        paginationDTO.setPagenum(pageInfo.getPageNum());
        paginationDTO.setLastpage(pageInfo.getPages());
        return paginationDTO;
    }
}
