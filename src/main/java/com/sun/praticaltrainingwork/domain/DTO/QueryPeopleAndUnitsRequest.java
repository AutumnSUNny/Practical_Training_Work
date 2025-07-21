package com.sun.praticaltrainingwork.domain.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QueryPeopleAndUnitsRequest {
    //身份证号
    @Schema(description = "身份证号",example = "123456789012345678")
    private String ID;
}
