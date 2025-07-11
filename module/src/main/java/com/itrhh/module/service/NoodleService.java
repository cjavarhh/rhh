package com.itrhh.module.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.itrhh.module.entity.Noodle;
import com.itrhh.module.mapper.NoodleMapper;
import com.itrhh.module.utils.NoodleJudgment;
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


    public List<Noodle> getAllNoodleInfo(Integer offset, Integer pageSize) {

        return nooodleMapper.getAll(offset, pageSize);
    }

    public Noodle getNoodleInfoById(BigInteger id) {

        return nooodleMapper.getById(id);
    }

    @Resource
    private NoodleMapper mapper;

    public int createNoodle(String name, Integer price, String content, Integer weight, String coverImages) {
        int timestamp = (int) (System.currentTimeMillis() / 100);
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
        return mapper.noodleInsert(noodle);

    }

    public int updateNoodle(BigInteger id, String name, Integer price, String content, Integer weight, String coverImages) {
        int timestamp = (int) (System.currentTimeMillis() / 100);
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

    public int deleteNoodle(BigInteger id) {
        return mapper.noodleDelete(id, (int) (System.currentTimeMillis() / 100));
    }

    //模糊查询
    public List<Noodle> getNoodleLike(String keyWord) {

        return nooodleMapper.getList(keyWord);
    }

    //分页模糊查询
    public PageInfo<Noodle> getNoodleList(Integer page, Integer pageSize, String keyWord) {
        PageHelper.startPage(page, pageSize);
        List<Noodle> noodleList = mapper.getList(keyWord);
        return new PageInfo<>(noodleList);

    }

    //根据id提取
    public Noodle extractById(Long id) {
        return mapper.extractById(id);
    }

    //更新
    public boolean update(Noodle noodle) {
        return mapper.update(noodle);
    }

    //插入
    public int insert(Noodle noodle) {
        return mapper.insert(noodle);
    }

    //删除
    public boolean delete(Long id) {
        return mapper.delete(id);
    }

    //合并新增修改方法
    public BigInteger edit(BigInteger id, String noodleName, Integer price, String content, Integer weight, String coverImages) {
        //判断参数是否合法
        NoodleJudgment.validateEntity(noodleName, coverImages, price);
        // 判断是新增还是更新
        if (id == null) {
            // 新增逻辑
            int timestamp = (int) (System.currentTimeMillis() / 100);
            Noodle noodle = new Noodle();
            noodle.setNoodleName(noodleName);
            noodle.setPrice(price);
            noodle.setNoodleWeight(weight);
            //noodle.setNoodleImage(noodleImage);
            noodle.setContent(content);
            noodle.setCoverImages(coverImages);
            noodle.setCreateTime(timestamp);
            noodle.setUpdateTime(timestamp);
            noodle.setIsDeleted(0);
            int insert = mapper.insert(noodle);
            return noodle.getId();

        } else {
            // 更新逻辑
            Noodle byId = nooodleMapper.getById(id);
            BigInteger id1 = byId.getId();
            if (id1 != id) {
                int timestamp = (int) (System.currentTimeMillis() / 100);
                Noodle noodle = new Noodle();
                noodle.setId(id);
                noodle.setNoodleName(noodleName);
                noodle.setNoodleWeight(weight);
                noodle.setPrice(price);
                noodle.setContent(content);
                // noodle.setNoodleImage(noodleImage);
                noodle.setCoverImages(coverImages);
                noodle.setUpdateTime(timestamp);
                mapper.update(noodle);
                return id;
            }
            throw new RuntimeException("id不存在，无法更新");
        }
    }
}
