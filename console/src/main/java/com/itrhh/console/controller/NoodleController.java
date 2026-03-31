package com.itrhh.console.controller;

import com.github.pagehelper.PageInfo;
import com.itrhh.console.domain.CategoryInfoVo;
import com.itrhh.console.domain.NoodleInfoVo;
import com.itrhh.console.domain.NoodleListVo;
import com.itrhh.console.utils.NoodleJudgment;
import com.itrhh.module.config.ResourceNotFoundException;
import com.itrhh.module.domin.NoodleVo;
import com.itrhh.module.entity.Category;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.CategoryService;
import com.itrhh.module.service.NoodleService;
import com.itrhh.console.domain.ResultConsoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/noodle/create")
    public ResponseEntity<?> noodleCreate(@RequestParam(name = "noodleName") String noodleName,
                                          @RequestParam(name = "price") BigDecimal price,
                                          @RequestParam(name = "weight") Integer weight,
                                          @RequestParam(name = "coverImages") String coverImages,
                                          @RequestParam(name = "content") String content,
                                          @RequestParam(name = "categoryId") Long categoryId
                                          // @RequestParam(name = "noodleImage") String noodleImage
    ) {
        String nameTrim = noodleName.trim();
        NoodleJudgment.validateEntity(noodleName, coverImages, price);
        try {

            BigInteger id = noodleService.edit(null, noodleName, price, content, weight, coverImages, categoryId);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @RequestMapping("/noodle/update")
    public ResponseEntity<?> noodleUpdate(@RequestParam(name = "noodleId") BigInteger noodleId,
                                          @RequestParam(name = "noodleName") String noodleName,
                                          @RequestParam(name = "price") BigDecimal price,
                                          @RequestParam(name = "weight") Integer weight,
                                          @RequestParam(name = "coverImages") String coverImages,
                                          @RequestParam(name = "content") String content,
                                          @RequestParam(name = "categoryId") Long categoryId
                                          //@RequestParam(name = "noodleImage") String noodleImage
    ) {
        String nameTrim = noodleName.trim();
        NoodleJudgment.validateEntity(noodleName, coverImages, price);
        try {
            BigInteger editUpdate = noodleService.edit(noodleId, noodleName, price, content, weight, coverImages, categoryId);
            return ResponseEntity.ok(noodleId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping("/noodle/delete")
    public String noodleDelete(@RequestParam(name = "noodleId") BigInteger noodleId) {
        Noodle noodleInfoById = noodleService.getNoodleInfoById(noodleId);
        Long cid = noodleInfoById.getCategoryId();
        if (cid == null) {
            throw new IllegalArgumentException("分类Id不能为空");
        }
        int i = categoryService.selectJudgeId(cid);
        if (i == 0) {
            throw new ResourceNotFoundException("分类id不存在");
        }
        categoryService.delete(cid);
        int result = noodleService.deleteNoodle(noodleId);
        return 1 == result ? "成功" : "失败";
    }

    @RequestMapping("/noodle/info")
    public NoodleInfoVo noodleConsoleInfo(@RequestParam(name = "noodleId") BigInteger noodleId) {
        Noodle noodleInfoConsoleById = noodleService.getNoodleInfoById(noodleId);
        NoodleInfoVo noodleConsoleInfoVo = new NoodleInfoVo();
        if (noodleInfoConsoleById == null) {
            System.out.println("没有拿到指定的id");
            return null;
        }
        String coverImages = noodleInfoConsoleById.getCoverImages();
        String[] split = coverImages.split("\\$");
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
    public ResultConsoleVo noodleConsoleAll(@RequestParam(name = "page") Integer page,
                                            @RequestParam(value = "keyword", required = false) String keyword) {
        Integer pageSize = 2;
        Integer offset = (page - 1) * pageSize;
        // List<Noodle> allNoodleInfo = noodleService.getAllNoodleInfo(offset, pageSize);
        PageInfo<Noodle> noodleVoPageInfo = noodleService.selectByNoodleOrCategory(offset, page, keyword);
        // PageInfo<NoodleVo> noodleVoPageInfo = noodleService.selectByNoodleOrCategory(page, keyword);
        List<Noodle> allNoodleInfo = noodleVoPageInfo.getList();
        ArrayList<NoodleListVo> noodleAppListVos = new ArrayList<>();
        //NoodleConsoleInfoVo noodleConsoleInfoVo = new NoodleConsoleInfoVo();
        NoodleListVo noodleConsoleListVo = new NoodleListVo();
        //NoodleVo noodleConsoleListVo = new NoodleVo();
        ResultConsoleVo<NoodleVo> resultConsoleVo = new ResultConsoleVo<>();
        //NoodleConsoleInfoVo noodleConsoleInfoVo = new NoodleConsoleInfoVo();
        for (Noodle noodle : allNoodleInfo) {
            Long categoryId = noodle.getCategoryId();
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getCategoryName();
            String coverImages = noodle.getCoverImages();
            String[] split = coverImages.split("\\$");
            String s = split[0];
            noodleConsoleListVo.setNoodleImage(s);
            noodleConsoleListVo.setNoodleId(noodle.getId());
            // noodleConsoleListVo.setNoodleId(noodle.getId());
            noodleConsoleListVo.setNoodleName(noodle.getNoodleName());
            noodleConsoleListVo.setPrice(noodle.getPrice());
            noodleAppListVos.add(noodleConsoleListVo);
            resultConsoleVo.setCategoryId(categoryId);
            resultConsoleVo.setCategoryName(categoryName);
        }
        resultConsoleVo.setData(noodleAppListVos);
        resultConsoleVo.setPagSize(pageSize);
        resultConsoleVo.setTotal(allNoodleInfo.size());
        return resultConsoleVo;
    }
}
