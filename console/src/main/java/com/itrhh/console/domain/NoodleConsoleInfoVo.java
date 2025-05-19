package com.itrhh.console.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname noodleConsoleVo
 * @Description TODO
 * @Created by 14195
 * @Date 2025/5/3 17:04
 * @Version 1.0.0
 */
@Data
public class NoodleConsoleInfoVo {
    private String noodleName;
    private List<String> noodleImages;
    private Integer price;
    private  Integer weight;
    private  String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
