package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author Amar_amo
 * @date 2019/12/7 0:23
 */
@Mapper
@Repository
public interface UserMapper {
    @Insert("Insert into user(email,password,name,account_id,bio,token,create_time,modified_time) values (#{email},#{password},#{name},#{account_id},#{bio},#{token},#{create_time},#{modified_time})")
    void Insert(User user);
    @Select("select * from user where token =#{token} ")
    User findByToken(@Param("token") String token);
    @Select("select * from user where email=#{email}")
    User findByEmail(@Param("email") String email);
    @Select("select * from user where account_id =#{account_id} ")
    User findByAccountId(@Param("account_id") String account_id);
    @Update("update user set token=#{token} where account_id = #{account_id} ")
    void updateByAccountId(@Param("account_id") String account_id,@Param("token") String token);
    @Update("update user set token=#{token} where email = #{email} ")
    void updateByEmail(@Param("email") String email,@Param("token") String token);
    @Select("select * from user where id =#{id}")
    User findById(@Param("id") long creator);
}
