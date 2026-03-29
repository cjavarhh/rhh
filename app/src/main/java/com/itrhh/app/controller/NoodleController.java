package com.itrhh.app.controller;

import com.github.pagehelper.PageInfo;
import com.itrhh.app.domain.*;
import com.itrhh.module.config.ResourceNotFoundException;
import com.itrhh.module.entity.Category;
import com.itrhh.module.entity.Noodle;
import com.itrhh.module.service.CategoryService;
import com.itrhh.module.service.NoodleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    private NoodleService noodleService;
    @Resource
    private CategoryService categoryService;


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
        Long cid = noodleInfoById.getCategoryId();
        if (cid == null) {
            throw new IllegalArgumentException("分类Id不能为空");
        }
        int i = categoryService.selectJudgeId(cid);
        if (i == 0) {
            throw new ResourceNotFoundException("分类id不存在");
        }
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
        //PageInfo<Noodle> noodleList = noodleService.getNoodleList(page, pageSize, keyword);
        PageInfo<Noodle> byNoodleOrCategory = noodleService.findByNoodleOrCategory(page, offset, keyword);
        List<Noodle> allNoodleInfo = byNoodleOrCategory.getList();
        ArrayList<NoodleListVo> noodleAppListVos = new ArrayList<>();
        ResultAppVo resultAppVo = new ResultAppVo();

        for (Noodle noodle : allNoodleInfo) {
            Long categoryId = noodle.getCategoryId();
            Category categoryInfo = categoryService.getById(categoryId);
            String categoryName = categoryInfo.getCategoryName();
            NoodleListVo noodleAppListVo = new NoodleListVo();
            String coverImages = noodle.getCoverImages();
            String[] split = coverImages.split("\\$");
            String s = split[0];
            noodleAppListVo.setNoodleImage(s);
            noodleAppListVo.setNoodleId(noodle.getId());
            noodleAppListVo.setNoodleName(noodle.getNoodleName());
            noodleAppListVos.add(noodleAppListVo);
            if (categoryId == null) {
                throw new IllegalArgumentException("分类Id不能为空");
            }
            int i = categoryService.selectJudgeId(categoryId);
            if (i == 0) {
                throw new ResourceNotFoundException("分类id不存在");
            }
            resultAppVo.setCategoryId(categoryId);
            //resultAppVo.setCategoryName(categoryName);
        }
        Boolean isEnd = allNoodleInfo.size() < pageSize;
        resultAppVo.setData(noodleAppListVos);
        resultAppVo.setIsEnd(isEnd);
        return resultAppVo;
    }

    @RequestMapping("/category/list")
    public List<CategoryInfoVo> categoryAll() {
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

    @RequestMapping("/app/noodle/List")
    public ResultAppVo listNoodle(@RequestParam(required = false) String Keyword, @RequestParam(name = "page") Integer page) {
        Integer pageSize = 2;
        Integer offset = (page - 1) * pageSize;
        List<Noodle> noodles = noodleService.searchNoodle(Keyword, offset, pageSize);
        ArrayList<NoodleListVo> noodleAppListVos = new ArrayList<>();
        ResultAppVo resultAppVo = new ResultAppVo();
        for (Noodle noodle : noodles) {
            Long categoryId = noodle.getCategoryId();
            Category categoryInfo = categoryService.getById(categoryId);
            String categoryName = categoryInfo.getCategoryName();
            NoodleListVo noodleAppListVo = new NoodleListVo();
            String coverImages = noodle.getCoverImages();
            String[] split = coverImages.split("\\$");
            String s = split[0];
            noodleAppListVo.setNoodleImage(s);
            noodleAppListVo.setNoodleId(noodle.getId());
            noodleAppListVo.setNoodleName(noodle.getNoodleName());
            noodleAppListVo.setCategoryName(categoryName);
            //noodleAppListVos.add(noodleAppListVo);
            if (categoryId == null) {
                throw new IllegalArgumentException("分类Id不能为空");
            }
            int i = categoryService.selectJudgeId(categoryId);
            if (i == 0) {
                throw new ResourceNotFoundException("分类id不存在");

            }
            noodleAppListVo.setCategoryId(categoryId);
            noodleAppListVos.add(noodleAppListVo);
        }
        Boolean isEnd = noodles.size() < pageSize;
        resultAppVo.setData(noodleAppListVos);
        resultAppVo.setIsEnd(isEnd);
        return resultAppVo;
    }


}










