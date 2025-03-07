package com.itrhh.module.service.impl;

import com.itrhh.module.entity.Noodle;
import com.itrhh.module.mapper.NoodleMapper;
import com.itrhh.module.service.ConsoleService;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @Classname ConsoleServiceImpl
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/19 20:12
 * @Version 1.0.0
 */
public class ConsoleServiceImpl implements ConsoleService {
    @Resource
    private NoodleMapper mapper;

    public int createNoodle(BigInteger id,String name,Integer price, String noodleImage,String content,Integer weight ,List coverImages){
        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
        noodle.setId(id);
        noodle.setNoodleName(name);
        noodle.setPrice(price);
        noodle.setNoodleWeight(weight);
        noodle.setNoodleImage(noodleImage);
        noodle.setContent(content);
        noodle.setCoverImages(coverImages);
        noodle.setCreateTime(timestamp);
        noodle.setUpdateTime(timestamp);
        noodle.setIsDeleted(0);
        return  mapper.noodleInsert(noodle);

    }



    public int updateNoodle(BigInteger id, String name, Integer price, String noodleImage, String content, Integer weight, List coverImages){

        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
        noodle.setId(id);
        noodle.setNoodleName(name);
        noodle.setNoodleWeight(weight);
        noodle.setPrice(price);
        noodle.setContent(content);
        noodle.setNoodleImage(noodleImage);
        noodle.setCoverImages(coverImages);
        noodle.setCreateTime(timestamp);
        noodle.setUpdateTime(timestamp);
        noodle.setIsDeleted(0);
        return mapper.noodleUpdate(noodle);
    }
public int deleteNoodle(BigInteger id){

        return mapper.noodleDelete(id);
}
}
