package com.example.community.mapper;

import com.example.community.model.Publish;
import com.example.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
