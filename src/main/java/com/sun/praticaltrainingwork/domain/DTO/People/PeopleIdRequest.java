package com.sun.praticaltrainingwork.domain.DTO.People;

import lombok.Data;

@Data
public class PeopleIdRequest {
    private Integer id; // 仅包含主键id，用于删除
}