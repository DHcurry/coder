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
    @Insert("INSERT INTO user (account_id,name,token,gmt_create,gmt_modify,avatar) VALUES(#{user.accountId},#{user.name},#{user.token},#{user.gmtCreate},#{user.gmtModify},#{user.avatar})")
    void add(@Param("user")User user);

    @Select("SELECT * FROM user WHERE token=#{token}")
    User findByToken(@Param(value = "token") String token);

    @Select("SELECT * FROM user WHERE account_id=#{id}")
    User findById(@Param("id") long id);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User findByInnerId(@Param("id") int id);

}
