package com.itrhh.app.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @Classname NoodleAppVo
 * @Description TODO
 * @Created by 14195
 * @Date 2024/11/7 23:25
 * @Version 1.0.0
 */
@Data
public class NoodleInfoVo {
    private String noodleName;
    private List coverImages;
    private String noodleImage;
    private int price;
    private int weight;
    private String content;
}
