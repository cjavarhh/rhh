package com.itrhh.module.domin;

import com.itrhh.module.entity.Category;
import com.itrhh.module.entity.Noodle;
import lombok.Data;

import java.math.BigInteger;

/**
 * @Classname NoodleVo
 * @Description TODO
 * @Created by 14195
 * @Date 2025/10/22 19:37
 * @Version 1.0.0
 */
@Data
public class NoodleVo  {
    private BigInteger id;

    private String noodleName;

    private Integer price;

    private String noodleImage;

    private String content;

    private Integer noodleWeight;
    //轮播图
    private String coverImages;

    private Integer createTime;

    private Integer updateTime;

    private Integer isDeleted;

    private Long categoryId;

    private Category category;
}
