package com.itrhh.module.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @Classname ConsoleService
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/19 20:11
 * @Version 1.0.0
 */
@Service
public interface ConsoleService {
    public int createNoodle(String name,Integer price, String[] noodleImages,String content,Integer weight ,String coverImage);
    public int updateNoodle(BigInteger id, String name, Integer price, String[] noodleImages, String content, Integer weight, String coverImage);
    public int deleteNoodle(BigInteger id);
}
