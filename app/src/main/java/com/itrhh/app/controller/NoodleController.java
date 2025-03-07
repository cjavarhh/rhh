package com.itrhh.app.controller;

import com.itrhh.app.domain.vo.NoodleAppListVo;
import com.itrhh.app.domain.vo.NoodleInfoVo;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.impl.AppServiceImpl;
import lombok.Data;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import java.awt.*;
import java.io.FileReader;
import java.math.BigInteger;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname AppController
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/18 9:44
 * @Version 1.0.0
 */
@RestController
public class NoodleController {
    String dbCoverImage="url1$url2$url3";
    String[] imageArray=dbCoverImage.split("\\$");
    List<String>imageList= Arrays.asList(imageArray);
    @Autowired
    private AppServiceImpl appService;
    @RequestMapping("/noodle/info")
    public NoodleInfoVo noodleInfo(@RequestParam(name = "noodleId")BigInteger noodleId){
       // String dbCoverImage="url1$url2$url3";
       // String[] imageArray=dbCoverImage.split("\\$");
        //List<String>imageList= Arrays.asList(imageArray);


        Noodle noodleInfoById = appService.getNoodleInfoById(noodleId);
        NoodleInfoVo noodleInfoVo = new NoodleInfoVo();
        if(noodleInfoById==null){
            System.out.println("代码有问题·");
            return null;
        }
        noodleInfoVo.setCoverImages(imageList);
        noodleInfoVo.setNoodleImage(noodleInfoById.getNoodleImage());
        noodleInfoVo.setNoodleName(noodleInfoById.getNoodleName());
        noodleInfoVo.setContent(noodleInfoById.getContent());
        noodleInfoVo.setPrice(noodleInfoById.getPrice());
        noodleInfoVo.setWeight(noodleInfoById.getNoodleWeight());
        return noodleInfoVo;
    }
    @RequestMapping("/noodle/list")
    public List<NoodleAppListVo> noodleAll(){
        List<Noodle> allNoodleInfo = appService.getAllNoodleInfo();
        ArrayList<NoodleAppListVo> noodleAppListVos = new ArrayList<>();
        NoodleAppListVo noodleAppListVo = new NoodleAppListVo();
        for (Noodle noodle : allNoodleInfo) {
            noodleAppListVo.setCoverImages(imageList);
            noodleAppListVo.setNoodleImage(noodle.getNoodleImage());
            noodleAppListVo.setNoodleId(noodle.getId());
            noodleAppListVo.setNoodleName(noodle.getNoodleName());
            noodleAppListVo.setPrice(noodle.getPrice());
            noodleAppListVos.add(noodleAppListVo);
        }
        return noodleAppListVos ;
    }


}
