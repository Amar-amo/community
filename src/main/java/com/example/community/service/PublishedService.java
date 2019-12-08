package com.example.community.service;

import com.example.community.dto.PublishDTO;
import com.example.community.mapper.PublishMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Publish;
import com.example.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 15:28
 */
@Service
public class PublishedService {
    @Autowired
    private PublishMapper publishMapper;
    @Autowired
    private UserMapper userMapper;

    public PublishDTO getById(long id) {
        Publish publish = publishMapper.getById(id);
        User user = userMapper.findById(publish.getCreator());
        PublishDTO publishDTO = new PublishDTO();
        BeanUtils.copyProperties(publish,publishDTO);
        publishDTO.setUser(user);
        return publishDTO;
    }
}
