package com.itrhh.console.controller;

import com.itrhh.app.domain.NoodleAppListVo;
import com.itrhh.app.domain.NoodleInfoVo;
import com.itrhh.console.domain.NoodleConsoleInfoVo;
import com.itrhh.console.domain.NoodleConsoleListVo;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.NoodleService;
import com.itrhh.module.vo.ResultConsoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 7@Classname ConsoleController
 *
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/18 9:43
 * @Version 1.0.0
 */
@RestController
public class NoodleConsoleController {
    @Autowired
    private NoodleService noodleService;

    @RequestMapping("/noodle/create")
    public String noodleCreate(@RequestParam(name = "noodleName") String noodleName,
                               @RequestParam(name = "price") Integer price,
                               @RequestParam(name = "weight") Integer weight,
                               @RequestParam(name = "coverImages") String coverImages,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "noodleImage") String noodleImage) {
        String nameTrim = noodleName.trim();


        int result = noodleService.createNoodle(noodleName, price, noodleImage, content, weight, coverImages);

        return 1 == result ? "成功" : "失败";
    }


    @RequestMapping("/noodle/update")
    public String noodleUpdate(@RequestParam(name = "noodleId") BigInteger noodleId,
                               @RequestParam(name = "noodleName") String noodleName,
                               @RequestParam(name = "price") Integer price,
                               @RequestParam(name = "weight") Integer weight,
                               @RequestParam(name = "coverImages") String coverImages,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "noodleImage") String noodleImage) {
        String nameTrim = noodleName.trim();
        int result = noodleService.updateNoodle(noodleId, noodleName, price, noodleImage, content, weight, coverImages);
        return 1 == result ? "成功" : "失败";
    }

    @RequestMapping("/noodle/delete")
    public String noodleDelete(@RequestParam(name = "noodleId") BigInteger noodleId) {
        int result = noodleService.deleteNoodle(noodleId);
        return 1 == result ? "成功" : "失败";
    }


    @RequestMapping("/noodleConsole/Info")
    public NoodleConsoleInfoVo noodleConsoleInfo(@RequestParam(name = "noodleId") BigInteger noodleId) {
        //String dbCoverImage="url1$url2$url3";
        //String[] imageArray=dbCoverImage.split("\\$");
        //List<String>imageList= Arrays.asList(imageArray);
        Noodle noodleInfoConsoleById = noodleService.getNoodleInfoById(noodleId);
        NoodleConsoleInfoVo noodleConsoleInfoVo = new NoodleConsoleInfoVo();
        //NoodleInfoVo noodleInfoVo = new NoodleInfoVo();
        if (noodleInfoConsoleById == null) {
            System.out.println("没有拿到指定的id");
            return null;
        }
        String coverImages = noodleInfoConsoleById.getCoverImages();
        String[] split = coverImages.split("\\$");
        // String toString = coverImages.toString();
        //String[] split = toString.split("\\$");
        List<String> imageList = Arrays.asList(split);
        noodleConsoleInfoVo.setNoodleImages(imageList);
        noodleConsoleInfoVo.setNoodleName(noodleInfoConsoleById.getNoodleName());
        noodleConsoleInfoVo.setContent(noodleInfoConsoleById.getContent());
        noodleConsoleInfoVo.setPrice(noodleInfoConsoleById.getPrice());
        noodleConsoleInfoVo.setWeight(noodleInfoConsoleById.getNoodleWeight());
        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime updateTime = createTime.plusHours(1);
        noodleConsoleInfoVo.setCreateTime(createTime);
        noodleConsoleInfoVo.setUpdateTime(updateTime);
        return noodleConsoleInfoVo;

    }

    @RequestMapping("/noodleConsole/list")
    public ResultConsoleVo noodleConsoleAll(@RequestParam(name = "offset") Integer offset, @RequestParam(name = "pageSize") Integer pageSize) {
        List<Noodle> allNoodleInfo = noodleService.getAllNoodleInfo(offset, pageSize);
        ArrayList<NoodleConsoleListVo> noodleAppListVos = new ArrayList<>();
        //NoodleConsoleInfoVo noodleConsoleInfoVo = new NoodleConsoleInfoVo();

        //NoodleAppListVo noodleAppListVo = new NoodleAppListVo();
        NoodleConsoleListVo noodleConsoleListVo = new NoodleConsoleListVo();
        ResultConsoleVo<Object> resultConsoleVo = new ResultConsoleVo<>();
        // NoodleConsoleInfoVo noodleConsoleInfoVo = new NoodleConsoleInfoVo();
        for (Noodle noodle : allNoodleInfo) {
            noodleConsoleListVo.setFeedImage(noodle.getNoodleImage());
            noodleConsoleListVo.setNoodleId(noodle.getId());
            // noodleConsoleListVo.setNoodleId(noodle.getId());
            noodleConsoleListVo.setNoodleName(noodle.getNoodleName());
            noodleConsoleListVo.setPrice(noodle.getPrice());
            noodleAppListVos.add(noodleConsoleListVo);
        }
        resultConsoleVo.setData(noodleAppListVos);
        resultConsoleVo.setPagSize(pageSize);
        resultConsoleVo.setTotal(allNoodleInfo.size());
        return resultConsoleVo;
    }

}
