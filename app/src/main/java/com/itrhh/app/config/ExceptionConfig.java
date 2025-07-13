package com.itrhh.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Classname ExceptionConfig
 * @Description TODO
 * @Created by 14195
 * @Date 2025/7/11 20:26
 * @Version 1.0.0
 */
@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        Logger logger = LoggerFactory.getLogger(handleException(e).getClass());
        logger.error("系统发生异常");
        return  e.getMessage();


    }

}
