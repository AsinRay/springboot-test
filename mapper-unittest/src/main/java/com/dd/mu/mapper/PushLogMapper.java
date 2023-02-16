package com.dd.mu.mapper;

import com.dd.mu.domain.PushLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PushLogMapper {
    @Select("select src,dest,msg,dt from pushlog where msg = #{msg}")
    List<PushLog> findByMsg(String msg);
}
