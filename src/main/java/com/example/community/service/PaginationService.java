package com.example.community.service;

import com.example.community.dto.IndexPaginationDTO;
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
public class PaginationService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishMapper publishMapper;

    public IndexPaginationDTO list(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        List<Publish> publishList =publishMapper.list();
        return getIndexPaginationDTO(publishList, userMapper);
    }
    public IndexPaginationDTO list(int pagenum, int pagesize,long id) {
        PageHelper.startPage(pagenum,pagesize);
        List<Publish> publishList =publishMapper.getListById(id);
        return getIndexPaginationDTO(publishList, userMapper);
    }

    static IndexPaginationDTO getIndexPaginationDTO(List<Publish> publishList, UserMapper userMapper) {
        PageInfo<Publish> pageInfo = new PageInfo<>(publishList);
        List<PublishDTO> publishDTOList=new ArrayList<>();
        for (Publish publish:publishList) {
            User user = userMapper.findById(publish.getCreator());
            PublishDTO publishDTO = new PublishDTO();
            BeanUtils.copyProperties(publish,publishDTO);
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
