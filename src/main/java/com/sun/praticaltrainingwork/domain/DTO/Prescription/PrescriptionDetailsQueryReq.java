package com.sun.praticaltrainingwork.domain.DTO.Prescription;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Schema(description = "处方明细查询请求参数")
public class PrescriptionDetailsQueryReq {

    @Nullable
    @Schema(description = "页码，默认第1页")
    private Integer pageNum = 1;  // 默认第1页

    @Nullable
    @Schema(description = "每页大小，默认每页10条")
    private Integer pageSize = 10;  // 默认每页10条

    @Nullable
    @Schema(description = "排序字段，key为字段名，value为是否升序（true：升序，false：降序）",
            example = "{\"amount\":false,\"projectName\":true}")
    private Map<String, Boolean> sorts;  // 排序条件

    @Nullable
    @Schema(description = "处方明细查询条件")
    private Conditions conditions;  // 封装查询条件的内部类

    /**
     * 处方明细查询条件内部类
     * 与MedicineQueryRequest保持一致的嵌套结构，便于前端统一处理
     */
    @Data
    public static class Conditions {  // 改为static内部类，支持外部实例化

        @Nullable
        @Schema(description = "处方明细ID")
        private Integer id;

        @Nullable
        @Schema(description = "住院号（关联就诊记录）")
        private String hospitalizationNumber;

        @Nullable
        @Schema(description = "收费项目类别")
        private String chargeableItemsCategory;

        @Nullable
        @Schema(description = "项目编码")
        private String projectCoding;

        @Nullable
        @Schema(description = "项目名称（支持模糊查询）")
        private String projectName;

        @Nullable
        @Schema(description = "单价（大于等于）")
        private BigDecimal unitPriceGte;  // 范围查询：大于等于

        @Nullable
        @Schema(description = "单价（小于等于）")
        private BigDecimal unitPriceLte;  // 范围查询：小于等于

        @Nullable
        @Schema(description = "数量（大于等于）")
        private BigDecimal quantityGte;

        @Nullable
        @Schema(description = "数量（小于等于）")
        private BigDecimal quantityLte;

        @Nullable
        @Schema(description = "金额（大于等于）")
        private BigDecimal amountGte;

        @Nullable
        @Schema(description = "金额（小于等于）")
        private BigDecimal amountLte;
    }
}