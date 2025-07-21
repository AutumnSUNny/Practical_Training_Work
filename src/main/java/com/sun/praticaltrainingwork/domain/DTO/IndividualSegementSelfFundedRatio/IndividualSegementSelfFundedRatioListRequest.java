package com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class IndividualSegementSelfFundedRatioListRequest {
    @Nullable
    @Schema(description = "页码")
    private Integer pageNum = 1;
    @Nullable
    @Schema(description = "每页大小")
    private Integer pageSize = 10;

    @Getter
    @Nullable
    @Schema(description = "排序字段", example = "{\"maximumAmount\":true,\"reimbursementProportion\":false}")
    private Map<String, Boolean> sorts;

    @Nullable
    @Schema(description = "查询条件")
    private Conditions conditions;

    @Data
    public class Conditions {
        @Nullable
        @Schema(description = "医疗类别")
        private String medicalCategory;
        @Nullable
        @Schema(description = "医疗人员类别")
        private String medicalPersonnelCategory;
        @Nullable
        @Schema(description = "医院等级")
        private String hospitalLevel;
        @Nullable
        @Schema(description = "上限金额")
        private BigDecimal maximumAmount;
        @Nullable
        @Schema(description = "下限金额")
        private BigDecimal minimumAmount;
        @Nullable
        @Schema(description = "报销比例")
        private BigDecimal reimbursementProportion;
    }
}