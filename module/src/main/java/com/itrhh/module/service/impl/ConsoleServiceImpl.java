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

    public int createNoodle(String name,Integer price, String[] noodleImages,String content,Integer weight ,String coverImage){
        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
        noodle.setNoodleName(name);
        noodle.setPrice(price);
        noodle.setNoodleWeight(weight);
        noodle.setNoodleImages(noodleImages);
        noodle.setContent(content);
        noodle.setCoverImage(coverImage);
        return  mapper.noodleInsert(noodle);

    }
    public int updateNoodle(BigInteger id,String name,Integer price,String[] noodleImages,String content,Integer weight,String coverImage){

        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
        noodle.setId(id);
        noodle.setNoodleName(name);
        noodle.setNoodleWeight(weight);
        noodle.setPrice(price);
        noodle.setContent(content);
        noodle.setNoodleImages(noodleImages);
        noodle.setCoverImage(coverImage);
        return mapper.noodleUpdate(noodle);
    }
public int deleteNoodle(BigInteger id){

        return mapper.noodleDelete(id);
}
}
