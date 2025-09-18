package com.itrhh.module.entity;

import lombok.Data;
import lombok.experimental.Accessors;


import java.math.BigInteger;
import java.util.List;

/**
 * @Classname Noodle
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/17 14:26
 * @Version 1.0.0
 */

//实体类
@Data

@Accessors(chain = true)
public class Noodle {

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

    private Long cid;


}
