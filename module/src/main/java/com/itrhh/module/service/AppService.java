package com.itrhh.module.service;

import com.itrhh.module.entity.Noodle;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname AppService
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/19 20:10
 * @Version 1.0.0
 */
@Service
public interface AppService {
    public List<Noodle> getAllNoodleInfo();
}
