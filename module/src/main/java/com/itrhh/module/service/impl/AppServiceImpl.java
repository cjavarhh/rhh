package com.itrhh.module.service.impl;

import com.itrhh.module.entity.Noodle;
import com.itrhh.module.mapper.NoodleMapper;
import com.itrhh.module.service.AppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @Classname AppServiceImpl
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/19 20:11
 * @Version 1.0.0
 */
@Service
public class AppServiceImpl implements AppService {
    @Resource
    private NoodleMapper nooodleMapper;

    @Override
    public List<Noodle> getAllNoodleInfo() {

        return nooodleMapper.getAll();

    }
    public  Noodle getNoodleInfoById(BigInteger id){
        return nooodleMapper.getById(id);
    }
}
