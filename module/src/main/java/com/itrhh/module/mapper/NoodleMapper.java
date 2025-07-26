package com.itrhh.module.mapper;

import com.itrhh.module.entity.Category;
import com.itrhh.module.entity.Noodle;
import org.apache.ibatis.annotations.*;
import org.yaml.snakeyaml.events.Event;

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
    Noodle getById(@Param("id") BigInteger id);

    //分页查询所有面条信息
    // @Select("select *from noodle limit #{offset},#{pageSize} where is_deleted=0")
    @Select("select * from noodle where is_deleted = 0 limit #{offset}, #{pageSize}")
    //List<Noodle> getAll();
    List<Noodle> getAll(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    //增加一个面条信息
    int noodleInsert(@Param("noodle") Noodle noodle);

    //修改一个面条信息
    int noodleUpdate(@Param("noodle") Noodle noodle);

    //删除一条面条信息
    @Update("update  noodle set is_deleted=1 ,update_time=#{updateTime} where id=#{id}")
    int noodleDelete(@Param("id") BigInteger id, @Param("updateTime") Integer updateTime);

    //模糊查询
    List<Noodle> getList(@Param("keyword") String keyword);

    @Select("select noodle_name, price from noodle wehere id=#{id} and is_deleted=0  ")
    Noodle extractById(@Param("id") Long id);

    boolean update(@Param("noodle") Noodle noodle);
   // @Options(useGeneratedKeys = true, keyProperty = "id")
    @Options(useGeneratedKeys = true, keyProperty = "id")
   int insert(@Param("noodle") Noodle noodle);

    @Update("update  noodel set is_deleted=1 wehere id=#{id}")
    boolean delete(@Param(("id")) Long id);

    @Select("select *from category where cid=#{cid} and is_deleted=0")
    Category selectCategoryById(@Param("cid") Integer cid);

    @Select("select *from category where  is_deleted=0")
    List<Category>getAllCategory ();

    //检查分类Id是否存在
    @Select("select *from category where cid=#{cid} and is_deleted=0")
    int selectById(@Param("cid") Integer cid);
}