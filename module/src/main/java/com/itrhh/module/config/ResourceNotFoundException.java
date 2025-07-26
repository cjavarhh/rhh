package com.itrhh.module.config;

/**
 * @Classname ResourceNotFoundException
 * @Description TODO
 * @Created by 14195
 * @Date 2025/7/25 12:36
 * @Version 1.0.0
 */
//自定义异常类
public class ResourceNotFoundException  extends  RuntimeException{
    public  ResourceNotFoundException (String message){
        super(message);
    }
}
