package com.itrhh.console.domain;

import lombok.Data;

import java.util.List;

@Data
public class ResultConsoleVo<T> {
    private List<?> data;
    private Integer total;
    private Integer pagSize;
}