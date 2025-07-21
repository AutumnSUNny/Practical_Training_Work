package com.sun.praticaltrainingwork.domain.DTO.PersonnelVisitsInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class PersonnelVisitsInfoQueryReq {
    @Nullable
    @Schema(description = "页码")
    private Integer pageNum = 1;  // 默认第1页

    @Nullable
    @Schema(description = "每页大小")
    private Integer pageSize = 10;  // 默认每页10条

    @Nullable
    @Schema(description = "排序字段，key为字段名，value为是否升序（true：升序，false：降序）",
            example = "{\"admissionDate\":false,\"hospitalGrade\":true}")
    private Map<String, Boolean> sorts;  // 排序条件

    @Nullable
    @Schema(description = "就诊信息查询条件")
    private Conditions conditions;  // 封装查询条件的内部类

    /**
     * 就诊信息查询条件内部类
     */
    @Data
    public static class Conditions {  // 修改为static，避免外部无法实例化
        @Nullable
        @Schema(description = "人员ID")
        private String personID;

        @Nullable
        @Schema(description = "住院号")
        private String hospitalizationNumber;

        @Nullable
        @Schema(description = "定点医疗机构编号")
        private String designatedNumber;

        @Nullable
        @Schema(description = "医疗类别")
        private String medicalCategory;

        @Nullable
        @Schema(description = "入院日期（开始）")
        private Date admissionDateStart;

        @Nullable
        @Schema(description = "入院日期（结束）")
        private Date admissionDateEnd;

        @Nullable
        @Schema(description = "出院日期（开始）")
        private Date dischargeDateStart;

        @Nullable
        @Schema(description = "出院日期（结束）")
        private Date dischargeDateEnd;

        @Nullable
        @Schema(description = "病种编码")
        private String diseaseCode;

        @Nullable
        @Schema(description = "医院等级")
        private String hospitalGrade;

        @Nullable
        @Schema(description = "入院诊断疾病编码")
        private String admissionCode;

        @Nullable
        @Schema(description = "入院诊断疾病名称")
        private String diagnosedName;

        @Nullable
        @Schema(description = "出院原因")
        private String dischargeReason;

        @Nullable
        @Schema(description = "结算标志")
        private String settlementFlag;
    }
}