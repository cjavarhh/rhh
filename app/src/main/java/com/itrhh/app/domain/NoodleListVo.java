package com.itrhh.app.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Classname NoodleAppListVo
 * @Description TODO
 * @Created by 14195
 * @Date 2025/3/19 23:08
 * @Version 1.0.0
 */
@Data
public class NoodleListVo {
    private BigInteger noodleId;
    private String noodleImage;
    private  String noodleName;
   // private Integer price;
}
