package com.sun.praticaltrainingwork.domain.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DiagnosisAddRequest{

    @Schema(description = "诊疗项目编码")
    private String diaId;

    @Schema(description = "诊疗项目名称")
    private String diaName;

    @Schema(description = "收费类别")
    private String diaExpType;

    @Schema(description = "收费项目等级")
    private String diaExpLevel;

    @Schema(description = "最高限价")
    private BigDecimal diaMaxPrize;

    @Schema(description = "开始日期")
    private Date diaStarttime;

    @Schema(description = "终止日期")
    private Date diaEndtime;

    @Schema(description = "有效标志")
    private String diaValid;

    @Schema(description = "医院等级")
    private String diaHosLevel;

    @Schema(description = "是否需要审批标志")
    private String diaApprovalmark;
}
