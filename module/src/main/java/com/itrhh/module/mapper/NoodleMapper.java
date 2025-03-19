package com.itrhh.module.mapper;

import com.itrhh.module.entity.Noodle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @Classname NoodleMapper
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/17 22:41
 * @Version 1.0.0
 */
@Mapper
public interface NoodleMapper {
    //查询编号面条信息
    @Select("select *from noodle where id=#{id} and is_deleted=0")
    Noodle getById(@Param("id")BigInteger id);

    //查询所有面条信息
    @Select("select *from noodle where is_deleted=0")
    List<Noodle> getAll();


    //增加一个面条信息
    int noodleInsert(@Param("noodle") Noodle noodle);

    //修改一个面条信息
    int noodleUpdate(@Param("noodle") Noodle noodle,@Param("id") BigInteger id);

    //删除一条面条信息
    int noodleDelete(@Param("id") BigInteger id,@Param("updateTime") Integer uptimeTime);
}