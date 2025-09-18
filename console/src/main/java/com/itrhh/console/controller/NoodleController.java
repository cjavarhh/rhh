package com.itrhh.console.controller;

import com.itrhh.console.domain.CategoryInfoVo;
import com.itrhh.console.domain.NoodleInfoVo;
import com.itrhh.console.domain.NoodleListVo;
import com.itrhh.console.utils.NoodleJudgment;
import com.itrhh.module.entity.Category;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.NoodleService;
import com.itrhh.console.domain.ResultConsoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class NoodleController {
    @Autowired
    private NoodleService noodleService;

    @RequestMapping("/noodle/create")
    public ResponseEntity<?> noodleCreate(@RequestParam(name = "noodleName") String noodleName,
                                          @RequestParam(name = "price") Integer price,
                                          @RequestParam(name = "weight") Integer weight,
                                          @RequestParam(name = "coverImages") String coverImages,
                                          @RequestParam(name = "content") String content,
                                          @RequestParam(name = "cid") Long cid
                                          // @RequestParam(name = "noodleImage") String noodleImage
    ) {
        String nameTrim = noodleName.trim();
        NoodleJudgment.validateEntity(noodleName, coverImages, price);
        try {

            BigInteger id = noodleService.edit(null, noodleName, price, content, weight, coverImages, cid);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @RequestMapping("/noodle/update")
    public ResponseEntity<?> noodleUpdate(@RequestParam(name = "noodleId") BigInteger noodleId,
                                          @RequestParam(name = "noodleName") String noodleName,
                                          @RequestParam(name = "price") Integer price,
                                          @RequestParam(name = "weight") Integer weight,
                                          @RequestParam(name = "coverImages") String coverImages,
                                          @RequestParam(name = "content") String content,
                                          @RequestParam(name = "cid") Long cid
                                          //@RequestParam(name = "noodleImage") String noodleImage
    ) {
        String nameTrim = noodleName.trim();
        NoodleJudgment.validateEntity(noodleName, coverImages, price);
        try {
            BigInteger editUpdate = noodleService.edit(noodleId, noodleName, price, content, weight, coverImages, cid);
            return ResponseEntity.ok(noodleId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping("/noodle/delete")
    public String noodleDelete(@RequestParam(name = "noodleId") BigInteger noodleId) {
        int result = noodleService.deleteNoodle(noodleId);
        return 1 == result ? "成功" : "失败";
    }

    @RequestMapping("/noodle/info")
    public NoodleInfoVo noodleConsoleInfo(@RequestParam(name = "noodleId") BigInteger noodleId) {
        //String dbCoverImage="url1$url2$url3";
        //String[] imageArray=dbCoverImage.split("\\$");
        //List<String>imageList= Arrays.asList(imageArray);
        Noodle noodleInfoConsoleById = noodleService.getNoodleInfoById(noodleId);
        NoodleInfoVo noodleConsoleInfoVo = new NoodleInfoVo();
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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime updateNow = now.plusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createTime = now.format(formatter);
        String updateTime = updateNow.format(formatter);
        noodleConsoleInfoVo.setCreateTime(createTime);
        noodleConsoleInfoVo.setUpdateTime(updateTime);
        return noodleConsoleInfoVo;
    }

    @RequestMapping("/noodle/list")
    public ResultConsoleVo noodleConsoleAll(@RequestParam(name = "page") Integer page) {
        Integer pageSize = 2;
        Integer offset = (page - 1) * pageSize;
        List<Noodle> allNoodleInfo = noodleService.getAllNoodleInfo(offset, pageSize);
        ArrayList<NoodleListVo> noodleAppListVos = new ArrayList<>();
        //NoodleConsoleInfoVo noodleConsoleInfoVo = new NoodleConsoleInfoVo();
        //NoodleAppListVo noodleAppListVo = new NoodleAppListVo();
        NoodleListVo noodleConsoleListVo = new NoodleListVo();
        ResultConsoleVo<Object> resultConsoleVo = new ResultConsoleVo<>();
        // NoodleConsoleInfoVo noodleConsoleInfoVo = new NoodleConsoleInfoVo();
        for (Noodle noodle : allNoodleInfo) {
            String coverImages = noodle.getCoverImages();
            String[] split = coverImages.split("\\$");
            String s = split[0];
            noodleConsoleListVo.setNoodleImage(s);
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







/*    @RequestMapping("/noodle/edit")
    public BigInteger editCreat (@RequestParam(name = "id") BigInteger id,
                          @RequestParam(name = "noodleName") String noodleName,
                          @RequestParam(name = "price") Integer price,
                          @RequestParam(name = "weight") Integer weight,
                          @RequestParam(name = "coverImages") String coverImages,
                          @RequestParam(name = "content") String content){
        String nameTrim = noodleName.trim();
        try {
            BigInteger edit = noodleService.edit(id, noodleName, price, content, weight, coverImages);

        }catch (Exception e){
            return e.getMessage();
        }


        return  id;
    }*/

/*    @RequestMapping("/noodle/editInsert")
    public ResponseEntity<?> editCreat(@RequestParam(name = "noodleName") String noodleName,
                                       @RequestParam(name = "price") Integer price,
                                       @RequestParam(name = "weight") Integer weight,
                                       @RequestParam(name = "coverImages") String coverImages,
                                       @RequestParam(name = "content") String content) {
        String nameTrim = noodleName.trim();
        try {

            BigInteger id = noodleService.edit(null, noodleName, price, content, weight, coverImages);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @RequestMapping("noodle/editUpdate")
    public ResponseEntity<?> editUpdate(@RequestParam(name = "id") BigInteger id,
                                        @RequestParam(name = "noodleName") String noodleName,
                                        @RequestParam(name = "price") Integer price,
                                        @RequestParam(name = "weight") Integer weight,
                                        @RequestParam(name = "coverImages") String coverImages,
                                        @RequestParam(name = "content") String content) {
        String trim = noodleName.trim();
        BigInteger edit = noodleService.edit(id, noodleName, price, content, weight, coverImages);
        try {
            BigInteger editUpdate = noodleService.edit(id, noodleName, price, content, weight, coverImages);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
}
