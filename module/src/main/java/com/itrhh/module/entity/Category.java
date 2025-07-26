package com.itrhh.module.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname Category
 * @Description TODO
 * @Created by 14195
 * @Date 2025/7/23 16:37
 * @Version 1.0.0
 */
@Data
@Accessors(chain = true)
public class Category {
    private Integer cid;
    private String categoryName;
    private String categoryImage;
    private Integer createTime;
    private Integer updateTime;
    private Integer isDeleted;

}
