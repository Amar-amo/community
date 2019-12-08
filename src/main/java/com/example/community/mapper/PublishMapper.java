package com.example.community.mapper;

import com.example.community.dto.PublishDTO;
import com.example.community.model.Publish;
import com.example.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/7 20:58
 */
@Mapper
@Repository
public interface PublishMapper {
    @Insert("Insert into publish(title,description,creator,create_time,modified_time,comment_count,view_count,like_count,tag) values (#{title},#{description},#{creator},#{create_time},#{modified_time},#{comment_count},#{view_count},#{like_count},#{tag})")
    void Insert(Publish publish);

    @Select("select * from publish")
    List<Publish> list();

    @Select("select * from publish where creator=#{id}")
    List<Publish> getListById(@Param("id") long id);

    @Select("select * from publish where id=#{id}")
    Publish getById(@Param("id") long id);
    @Update("update publish set title=#{title},description=#{description},modified_time=#{modified_time},tag=#{tag},comment_count=#{comment_count},view_count=#{view_count} where id=#{id}")
    void updateById(Publish publish);
}
