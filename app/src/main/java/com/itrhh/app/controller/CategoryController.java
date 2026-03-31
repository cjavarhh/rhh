package com.itrhh.app.controller;

import com.itrhh.app.domain.CategoryInfoVo;
import com.itrhh.module.entity.Category;
import com.itrhh.module.service.CategoryService;
import com.itrhh.module.service.NoodleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CategoryController
 * @Description TODO
 * @Created by 14195
 * @Date 2026/3/31 21:16
 * @Version 1.0.0
 */
@RestController
public class CategoryController {
    @Resource
    private NoodleService noodleService;
    @Resource
    private CategoryService categoryService;
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
}
