package com.itrhh.module.service;

import com.itrhh.module.entity.Noodle;
import com.itrhh.module.mapper.NoodleMapper;
import org.springframework.stereotype.Service;

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
@Service
public class ConsoleService {
    @Resource
    private NoodleMapper mapper;

    public int createNoodle(String name,Integer price, String noodleImage,String content,Integer weight ,String coverImages){
        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
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



    public int updateNoodle(BigInteger id, String name, Integer price, String noodleImage, String content, Integer weight, String coverImages){

        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
        noodle.setNoodleName(name);
        noodle.setNoodleWeight(weight);
        noodle.setPrice(price);
        noodle.setContent(content);
        noodle.setNoodleImage(noodleImage);
        noodle.setCoverImages(coverImages);
        noodle.setUpdateTime(timestamp);
        return mapper.noodleUpdate(noodle,id);
    }
public int deleteNoodle(BigInteger id,Integer updateTime){
        return mapper.noodleDelete(id,updateTime);
}
}
