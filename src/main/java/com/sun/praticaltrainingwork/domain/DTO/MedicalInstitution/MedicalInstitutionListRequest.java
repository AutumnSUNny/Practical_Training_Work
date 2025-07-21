package com.sun.praticaltrainingwork.domain.DTO.MedicalInstitution;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class MedicalInstitutionListRequest {
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
        @Schema(description = "医疗机构ID")
        private String instId;

        @Nullable
        @Schema(description = "医疗机构名称")
        private String instName;

        @Nullable
        @Schema(description = "医疗机构经验类型")
        private String instExpType;

        @Nullable
        @Schema(description = "医疗机构经验等级")
        private String instExpLevel;

        @Nullable
        @Schema(description = "最高奖项金额")
        private BigDecimal instMaxPrize;

        @Nullable
        @Schema(description = "开始时间")
        private Date instStarttime;

        @Nullable
        @Schema(description = "结束时间")
        private Date instEndtime;

        @Nullable
        @Schema(description = "是否有效", example = "1")
        private String instValid;

        @Nullable
        @Schema(description = "医院等级")
        private String instHosLevel;

        @Nullable
        @Schema(description = "审批标志")
        private String instApprovalmark;
    }
}
