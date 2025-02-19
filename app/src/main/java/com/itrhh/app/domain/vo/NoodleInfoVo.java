package com.itrhh.app.domain.vo;

import lombok.Data;

/**
 * @Classname NoodleAppVo
 * @Description TODO
 * @Created by 14195
 * @Date 2024/11/7 23:25
 * @Version 1.0.0
 */
@Data
public class NoodleInfoVo {
    String noodleName;
    String[] noodleImages;
    int price;
    int weight;
    String content;
}
