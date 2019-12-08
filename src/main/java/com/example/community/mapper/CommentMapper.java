package com.example.community.mapper;

import com.example.community.model.Comment;
import com.example.community.model.Publish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 20:24
 */
@Mapper
@Repository
public interface CommentMapper {
    @Insert("Insert into comment(id,commentator,content,like_count.create_time,modified_time,page_id,read_mark) values (#{id},#{commentator},#{content},#{like_count},#{create_time},#{modified_time},#{page_id},#{read_mark})")
    void Insert(Comment publish);

    @Select("select * from comment where page_id=#{id}")
    List<Comment> findByPageId(@Param("id") long id);
}
