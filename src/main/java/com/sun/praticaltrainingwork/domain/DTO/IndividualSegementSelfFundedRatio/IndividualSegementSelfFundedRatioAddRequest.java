package com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class IndividualSegementSelfFundedRatioAddRequest {
    private String medicalCategory;
    private String medicalPersonnelCategory;
    private String hospitalLevel;
    private BigDecimal maximumAmount;
    private BigDecimal minimumAmount;
    private BigDecimal reimbursementProportion;
}