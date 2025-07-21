package com.sun.praticaltrainingwork.domain.DTO.Company;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import java.util.Map;

@Data
public class CompanyListRequest {
    @Nullable
    @Schema(description = "页码")
    private Integer pageNum = 1;

    @Nullable
    @Schema(description = "每页大小")
    private Integer pageSize = 10;

    @Getter
    @Nullable
    @Schema(description = "排序字段", example = "{\"companyName\":true,\"companyId\":false}")
    private Map<String, Boolean> sorts;

    @Nullable
    @Schema(description = "查询条件")
    private Conditions conditions;

    @Data
    public class Conditions {
        @Nullable
        @Schema(description = "单位编号")
        private String companyId;

        @Nullable
        @Schema(description = "单位名称")
        private String companyName;

        @Nullable
        @Schema(description = "单位类型（0/1/2分类）")
        private String companyType;

        @Nullable
        @Schema(description = "地址")
        private String address;

        @Nullable
        @Schema(description = "邮编")
        private String postcode;

        @Nullable
        @Schema(description = "联系电话")
        private String phoneNumber;
    }
}