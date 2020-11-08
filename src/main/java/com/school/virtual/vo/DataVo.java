package com.school.virtual.vo;

import lombok.Data;

import java.util.List;

@Data
public class DataVo<T> {
    private Integer code;
    private String msg;
    private Long count;//总条数
    private Long curr;//当前页
    private List<T> data;
}
