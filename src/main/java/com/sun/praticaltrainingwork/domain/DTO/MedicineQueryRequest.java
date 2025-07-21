package com.sun.praticaltrainingwork.domain.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class MedicineQueryRequest {
    @Nullable
    @Schema(description = "页码")
    private Integer pageNum = 1;  // 默认第1页

    @Nullable
    @Schema(description = "每页大小")
    private Integer pageSize = 10;  // 默认每页10条

    @Nullable
    @Schema(description = "排序字段，key为字段名，value为是否升序（true：升序，false：降序）",
            example = "{\"medName\":true,\"medMaxPrice\":false}")
    private Map<String, Boolean> sorts;  // 排序条件

    @Nullable
    @Schema(description = "药品查询条件")
    private Conditions conditions;  // 封装查询条件的内部类

    /**
     * 药品查询条件内部类
     * 与DiagnosisListRequest保持一致的嵌套结构，便于前端统一处理
     */
    @Data
    public class Conditions {  // 修改为static，避免外部无法实例化
        @Nullable
        @Schema(description = "药品ID")
        private String medId;

        @Nullable
        @Schema(description = "药品名称")
        private String medName;

        @Nullable
        @Schema(description = "药品类型")
        private String medExpType;

        @Nullable
        @Schema(description = "药品级别")
        private String medExpLevel;

        @Nullable
        @Schema(description = "药品计量单位")
        private String medMeasurement;

        @Nullable
        @Schema(description = "药品最高价格")
        private BigDecimal medMaxPrice;

        @Nullable
        @Schema(description = "药品审批标志")
        private String medApprovalmark;

        @Nullable
        @Schema(description = "适用医院级别")
        private String medHosLevel;

        @Nullable
        @Schema(description = "药品规格")
        private String medSize;

        @Nullable
        @Schema(description = "药品商品名")
        private String medTradename;

        @Nullable
        @Schema(description = "有效开始时间")
        private Date medStarttime;

        @Nullable
        @Schema(description = "有效结束时间")
        private Date medEndtime;

        @Nullable
        @Schema(description = "是否有效（Y/N）")
        private String medValid;

        @Nullable
        @Schema(description = "特殊标识")
        private String medSpecialmark;
    }
}