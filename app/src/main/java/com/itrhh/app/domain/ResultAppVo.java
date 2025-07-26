package com.itrhh.app.domain;

import lombok.Data;

import java.util.List;

/**
 * @Classname ResultAppVo
 * @Description TODO
 * @Created by 14195
 * @Date 2025/5/26 20:54
 * @Version 1.0.0
 */
@Data
public class ResultAppVo {
    private List<?> data;
    private Boolean isEnd;
    private String  categoryName;
}
