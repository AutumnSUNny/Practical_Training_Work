package com.sun.praticaltrainingwork.domain.VO;

import lombok.Data;

import java.util.List;

@Data
public class QueryVO<T>{
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<T> data;
}
