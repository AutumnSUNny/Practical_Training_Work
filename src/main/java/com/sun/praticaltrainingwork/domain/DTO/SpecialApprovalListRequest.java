package com.sun.praticaltrainingwork.domain.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.Map;

@Data
public class SpecialApprovalListRequest {
    @Nullable
    @Schema(description = "页码")
    private Integer pageNum = 1;

    @Nullable
    @Schema(description = "每页大小")
    private Integer pageSize = 10;

    @Getter
    @Nullable
    @Schema(description = "排序字段", example = "{\"name\":true,\"brithday\":false}")
    private Map<String, Boolean> sorts;

    @Nullable
    @Schema(description = "查询条件")
    private Conditions conditions;

    @Data
    public class Conditions {

        @Nullable
        @Schema(description = "审批编号")
        private String approvalNumber;

        @Nullable
        @Schema(description = "人员ID")
        private String personID;

        @Nullable
        @Schema(description = "审批类别")
        private String approvalCategory;

        @Nullable
        @Schema(description = "开始日期")
        private Date startDate;

        @Nullable
        @Schema(description = "终止日期")
        private Date terminationDate;

        @Nullable
        @Schema(description = "药品编码")
        private String drugCode;

        @Nullable
        @Schema(description = "审批意见")
        private String approvalOpinions;

        @Nullable
        @Schema(description = "审批人")
        private String approver;

        @Nullable
        @Schema(description = "审批日期")
        private Date approvalDate;

        @Nullable
        @Schema(description = "审批标志")
        private String approvalFlag;

    }
}
