package com.deng.coder.mapper;

import com.deng.coder.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("INSERT INTO user (account_id,name,token,gmt_create,gmt_modify) VALUES(#{user.accountId},#{user.name},#{user.token},#{user.gmtCreate},#{user.gmtModify})")
    void add(@Param("user")User user);

    @Select("SELECT * FROM user WHERE token=#{token}")
    User findByToken(@Param(value = "token") String token);
}
