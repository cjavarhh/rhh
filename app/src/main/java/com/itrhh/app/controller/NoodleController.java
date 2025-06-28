package com.itrhh.app.controller;

import com.github.pagehelper.PageInfo;
import com.itrhh.app.domain.NoodleListVo;
import com.itrhh.app.domain.NoodleInfoVo;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.NoodleService;
import com.itrhh.app.domain.ResultAppVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
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
    //private  Integer total =7;
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
        noodleInfoVo.setCoverImages(imageList);
        noodleInfoVo.setNoodleName(noodleInfoById.getNoodleName());
        noodleInfoVo.setContent(noodleInfoById.getContent());
        noodleInfoVo.setPrice(noodleInfoById.getPrice());
        noodleInfoVo.setWeight(noodleInfoById.getNoodleWeight());
        return noodleInfoVo;
    }
    @RequestMapping("/noodle/list")
    public ResultAppVo noodleAll(@RequestParam(name = "page") Integer page, @RequestParam(value = "keyword", required = false) String keyword) {
        Integer pageSize = 2;
        Integer offset = (page - 1) * pageSize;
        // List<Noodle> noodleLike = noodleService.getNoodleLike(keyWord);
        //List<Noodle> allNoodleInfo = noodleService.getAllNoodleInfo(offset, pageSize);
        PageInfo<Noodle> noodleList = noodleService.getNoodleList(page, pageSize, keyword);
        List<Noodle> allNoodleInfo = noodleList.getList();
        ArrayList<NoodleListVo> noodleAppListVos = new ArrayList<>();
        ResultAppVo resultAppVo = new ResultAppVo();
        NoodleListVo noodleAppListVo = new NoodleListVo();
        for (Noodle noodle : allNoodleInfo) {
            String coverImages = noodle.getCoverImages();
            String[] split = coverImages.split("\\$");
            String s = split[0];
            noodleAppListVo.setNoodleImage(s);
            noodleAppListVo.setNoodleId(noodle.getId());
            noodleAppListVo.setNoodleName(noodle.getNoodleName());
            // noodleAppListVo.setPrice(noodle.getPrice());
            noodleAppListVos.add(noodleAppListVo);
        }
        // Boolean isEnd= allNoodleInfo.size()>total?true:false;
        Boolean isEnd = allNoodleInfo.size() < pageSize;
        noodleAppListVos.add(noodleAppListVo);
        resultAppVo.setData(noodleAppListVos);
        resultAppVo.setIsEnd(isEnd);
        return resultAppVo;
    }
}
