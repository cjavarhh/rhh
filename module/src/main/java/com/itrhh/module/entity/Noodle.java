package com.itrhh.module.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

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

    private  int price;

    private String[] noodleImages;

    private String content;

    private  int noodleWeight;

    private String coverImage;


}
