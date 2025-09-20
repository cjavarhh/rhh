package com.itrhh.app.controller;

import com.github.pagehelper.PageInfo;
import com.itrhh.app.domain.CategoryInfoVo;
import com.itrhh.app.domain.NoodleListVo;
import com.itrhh.app.domain.NoodleInfoVo;
import com.itrhh.module.entity.Category;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.CategoryService;
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
    @Autowired
    private NoodleService noodleService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/noodle/info")
    public NoodleInfoVo noodleInfo(@RequestParam(name = "noodleId") BigInteger noodleId) {
        Noodle noodleInfoById = noodleService.getNoodleInfoById(noodleId);
        NoodleInfoVo noodleInfoVo = new NoodleInfoVo();
        if (noodleInfoById == null) {
            System.out.println("没有拿到指定的id");
            return null;
        }
        String coverImages = noodleInfoById.getCoverImages();
        String[] split = coverImages.split("\\$");
        Long cid = noodleInfoById.getCid();
        Category category = categoryService.getById(cid);
        String categoryImage = category.getCategoryImage();
        String categoryName = category.getCategoryName();
        List<String> imageList = Arrays.asList(split);
        noodleInfoVo.setCoverImages(imageList);
        noodleInfoVo.setNoodleName(noodleInfoById.getNoodleName());
        noodleInfoVo.setContent(noodleInfoById.getContent());
        noodleInfoVo.setPrice(noodleInfoById.getPrice());
        noodleInfoVo.setWeight(noodleInfoById.getNoodleWeight());
        noodleInfoVo.setCategoryName(categoryName);
        noodleInfoVo.setCategoryImage(categoryImage);
        return noodleInfoVo;
    }

    @RequestMapping("/noodle/list")
    public ResultAppVo noodleAll(@RequestParam(name = "page") Integer page,
                                 @RequestParam(value = "keyword", required = false) String keyword) {
        Integer pageSize = 2;
        Integer offset = (page - 1) * pageSize;
        PageInfo<Noodle> noodleList = noodleService.getNoodleList(page, pageSize, keyword);
        List<Noodle> allNoodleInfo = noodleList.getList();
        ArrayList<NoodleListVo> noodleAppListVos = new ArrayList<>();
        ResultAppVo resultAppVo = new ResultAppVo();
        for (Noodle noodle : allNoodleInfo) {
            NoodleListVo noodleAppListVo = new NoodleListVo();
            String coverImages = noodle.getCoverImages();
            String[] split = coverImages.split("\\$");
            String s = split[0];
            noodleAppListVo.setNoodleImage(s);
            noodleAppListVo.setNoodleId(noodle.getId());
            noodleAppListVo.setNoodleName(noodle.getNoodleName());
            noodleAppListVos.add(noodleAppListVo);
            Long cid = noodle.getCid();
            Category category = categoryService.getById(cid);
            String categoryName = category.getCategoryName();
            resultAppVo.setCategoryName(categoryName);
            noodleAppListVos.add(noodleAppListVo);
        }
        Boolean isEnd = allNoodleInfo.size() < pageSize;
        resultAppVo.setData(noodleAppListVos);
        resultAppVo.setIsEnd(isEnd);
        return resultAppVo;
    }

    @RequestMapping("/category/list")
    public List< CategoryInfoVo> categoryAll() {
        List<Category> categoryAll = categoryService.getAllCategoryInfo();
        ArrayList<CategoryInfoVo> categoryInfoVos = new ArrayList<>();
        for (Category category : categoryAll) {
            CategoryInfoVo categoryInfoVo = new CategoryInfoVo();
            categoryInfoVo.setCategoryImage(category.getCategoryImage());
            categoryInfoVo.setCategoryName(category.getCategoryName());
            categoryInfoVo.setCId(category.getId());
            categoryInfoVos.add(categoryInfoVo);
        }
        return categoryInfoVos;
    }
}
