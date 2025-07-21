package com.sun.praticaltrainingwork.domain.DTO.People;


import lombok.Data;

@Data
public class PeopleUpdRequest extends PeopleAddRequest {
    private Integer id; // 继承新增请求的所有字段，并添加主键id
}