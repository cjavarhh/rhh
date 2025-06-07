package com.itrhh.console.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Classname noodleConsoleListVo
 * @Description TODO
 * @Created by 14195
 * @Date 2025/5/3 17:05
 * @Version 1.0.0
 */
@Data
public class NoodleListVo {
    private BigInteger noodleId;
    private String noodleImage;
    private String noodleName;
    private Integer price;

}
