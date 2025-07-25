package com.sun.praticaltrainingwork.domain.VO.Diagnosis;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TDiagnosisProjectVO {
    private Integer id;

    private String diaId;

    private String diaName;

    private String diaExpType;

    private String diaExpLevel;

    private BigDecimal diaMaxPrize;

    private Date diaStarttime;

    private Date diaEndtime;

    private String diaValid;

    private String diaHosLevel;

    private String diaApprovalmark;
}
