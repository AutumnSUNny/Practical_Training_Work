package com.sun.praticaltrainingwork.domain.DTO.Company;

import lombok.Data;

@Data
public class CompanyUpdRequest extends CompanyAddRequest {
    private Integer id; // 继承新增请求字段，添加主键id
}
