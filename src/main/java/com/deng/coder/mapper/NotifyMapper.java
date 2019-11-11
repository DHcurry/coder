package com.deng.coder.mapper;

import com.deng.coder.models.Notify;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface NotifyMapper {

    @Insert("INSERT INTO notify (writer_id,receiver,article_id,gmt_create,status,type) VALUES(#{writerId},#{receiver},#{articleId},#{gmtCreate},#{status},#{type})")
    void add(Notify notify);

    @Select("SELECT * FROM notify WHERE status=1")
    ArrayList<Notify> getUnreadList();


    @Update("UPDATE notify SET status = 0 WHERE article_id=#{articleId}")
    void cancel(@Param("articleId") int articleId);
}
