package com.sun.praticaltrainingwork.domain.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Map;

@Data
public class DiagnosisListRequest {
    @Nullable
    @Schema(description = "页码")
    private Integer pageNum = 1;
    @Nullable
    @Schema(description = "每页大小")
    private Integer pageSize = 10;

    @Getter
    @Nullable
    @Schema(description = "排序字段", example = "{\"name\":true,\"age\":false}")
    private Map<String,Boolean> sorts;

    @Nullable
    @Schema(description = "查询条件")
    private Conditions conditions;

    @Data
    public class Conditions{
        @Nullable
        @Schema(description = "项目ID")
        private String diaId;

        @Nullable
        @Schema(description = "项目名称")
        private String diaName;

        @Nullable
        @Schema(description = "项目类型")
        private String diaExpType;

        @Nullable
        @Schema(description = "项目级别")
        private String diaExpLevel;

        @Nullable
        @Schema(description = "项目最高奖金额")
        private BigDecimal diaMaxPrize;

        @Nullable
        @Schema(description = "项目开始时间")
        private Date diaStarttime;

        @Nullable
        @Schema(description = "项目结束时间")
        private Date diaEndtime;

        @Nullable
        @Schema(description = "项目是否有效")
        private String diaValid;

        @Nullable
        @Schema(description = "项目医院级别")
        private String diaHosLevel;

        @Nullable
        @Schema(description = "项目审批标志")
        private String diaApprovalmark;
    }
}
