package com.itrhh.app.controller;

import com.itrhh.app.domain.NoodleAppListVo;
import com.itrhh.app.domain.NoodleInfoVo;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.NoodleService;
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
    private  Integer total =7;
    //String dbCoverImage="url1$url2$url3";
    //String[] imageArray=dbCoverImage.split("\\$");
    //List<String>imageList= Arrays.asList(imageArray);
    @Autowired
    private NoodleService noodleService;

    @RequestMapping("/noodle/info")
    public NoodleInfoVo noodleInfo(@RequestParam(name = "noodleId") BigInteger noodleId) {
        //String dbCoverImage="url1$url2$url3";
        //String[] imageArray=dbCoverImage.split("\\$");
        //List<String>imageList= Arrays.asList(imageArray);


        Noodle noodleInfoById = noodleService.getNoodleInfoById(noodleId);
        NoodleInfoVo noodleInfoVo = new NoodleInfoVo();
        if (noodleInfoById == null) {
            System.out.println("没有拿到指定的id");
            return null;
        }
        String coverImages = noodleInfoById.getCoverImages();
        String[] split = coverImages.split("\\$");
        // String toString = coverImages.toString();
        //String[] split = toString.split("\\$");
        List<String> imageList = Arrays.asList(split);
        noodleInfoVo.setNoodleImages(imageList);
        noodleInfoVo.setNoodleName(noodleInfoById.getNoodleName());
        noodleInfoVo.setContent(noodleInfoById.getContent());
        noodleInfoVo.setPrice(noodleInfoById.getPrice());
        noodleInfoVo.setWeight(noodleInfoById.getNoodleWeight());
        return noodleInfoVo;
    }


    @RequestMapping("/noodle/list")
    public List<NoodleAppListVo> noodleAll(@RequestParam(name = "offset") Integer offset,@RequestParam(name = "pageSize") Integer pageSize) {
        List<Noodle> allNoodleInfo = noodleService.getAllNoodleInfo(offset, pageSize);
        ArrayList<NoodleAppListVo> noodleAppListVos = new ArrayList<>();
        NoodleAppListVo noodleAppListVo = new NoodleAppListVo();
        for (Noodle noodle : allNoodleInfo) {
            noodleAppListVo.setFeedImage(noodle.getNoodleImage());
            noodleAppListVo.setNoodleId(noodle.getId());
            noodleAppListVo.setNoodleName(noodle.getNoodleName());
            noodleAppListVo.setPrice(noodle.getPrice());
            noodleAppListVos.add(noodleAppListVo);
        }
        Boolean isEnd= allNoodleInfo.size()>total?true:false;
        noodleAppListVo.setIsEnd(isEnd);
        noodleAppListVos.add(noodleAppListVo) ;
        return noodleAppListVos;
    }


}
