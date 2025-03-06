package com.itrhh.module.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @Classname ConsoleService
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/19 20:11
 * @Version 1.0.0
 */
@Service
public interface ConsoleService {
    public int createNoodle(BigInteger id,String name,Integer price, String noodleImage,String content,Integer weight ,List coverImages);
    public int updateNoodle(BigInteger id, String name, Integer price, String noodleImage, String content, Integer weight, List coverImages);
    public int deleteNoodle(BigInteger id);
}
