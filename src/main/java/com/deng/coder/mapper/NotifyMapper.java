package com.deng.coder.mapper;

import com.deng.coder.models.Notify;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NotifyMapper {

    @Insert("INSERT INTO notify (writer_id,receiver,gmt_create,status,type) VALUES(#{writerId},#{receiver},#{gmtCreate},#{status},#{type})")
    void add(Notify notify);
}
