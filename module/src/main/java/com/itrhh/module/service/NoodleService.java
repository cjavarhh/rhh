package com.itrhh.module.service;

import com.itrhh.module.entity.Noodle;
import com.itrhh.module.mapper.NoodleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @Classname NoodleService
 * @Description TODO
 * @Created by 14195
 * @Date 2025/3/20 21:08
 * @Version 1.0.0
 */
@Service
public class NoodleService {
    @Resource
    private NoodleMapper nooodleMapper;

    public List<Noodle> getAllNoodleInfo(Integer offset,Integer pageSize) {

        return nooodleMapper.getAll(offset,pageSize);
    }

    public  Noodle getNoodleInfoById(BigInteger id){

        return nooodleMapper.getById(id);
    }
    @Resource
    private NoodleMapper mapper;

    public int createNoodle(String name,Integer price,String content,Integer weight ,String coverImages){
        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
        noodle.setNoodleName(name);
        noodle.setPrice(price);
        noodle.setNoodleWeight(weight);
        //noodle.setNoodleImage(noodleImage);
        noodle.setContent(content);
        noodle.setCoverImages(coverImages);
        noodle.setCreateTime(timestamp);
        noodle.setUpdateTime(timestamp);
        noodle.setIsDeleted(0);
        return  mapper.noodleInsert(noodle);

    }



    public int updateNoodle(BigInteger id, String name, Integer price, String content, Integer weight, String coverImages){
        int timestamp =(int) (System.currentTimeMillis()/100);
        Noodle noodle = new Noodle();
        noodle.setId(id);
        noodle.setNoodleName(name);
        noodle.setNoodleWeight(weight);
        noodle.setPrice(price);
        noodle.setContent(content);
       // noodle.setNoodleImage(noodleImage);
        noodle.setCoverImages(coverImages);
        noodle.setUpdateTime(timestamp);
        return mapper.noodleUpdate(noodle);
    }
    public int deleteNoodle(BigInteger id){
        return mapper.noodleDelete(id,(int)(System.currentTimeMillis()/100));
    }


    public List<Noodle> getNoodleLike (String keyWorld){

        return  nooodleMapper.getList(keyWorld);
    }


    //根据id提取
    public  Noodle extractById(Long id){
        return mapper.extractById(id);
    }
    //更新
    public boolean update(Noodle noodle){
        return mapper.update(noodle);
    }
    //插入
    public  boolean insert(Noodle noodle){
        return mapper.insert(noodle);
    }
    //删除
    public boolean deleted(Long id){
        return mapper.deleted(id);
    }
}
