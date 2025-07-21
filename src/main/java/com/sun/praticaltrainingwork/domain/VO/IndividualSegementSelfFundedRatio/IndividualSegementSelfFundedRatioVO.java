package com.sun.praticaltrainingwork.domain.VO.IndividualSegementSelfFundedRatio;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class IndividualSegementSelfFundedRatioVO {
    private Integer id;
    private String medicalCategory;
    private String medicalPersonnelCategory;
    private String hospitalLevel;
    private BigDecimal maximumAmount;
    private BigDecimal minimumAmount;
    private BigDecimal reimbursementProportion;
}
