package com.sun.praticaltrainingwork.domain.VO.MinimumPaymentStandard;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class MinimumPaymentStandardVO {
    private Integer id;
    private String medicalCategory;
    private String medicalPersonnelCategory;
    private String hospitalLevel;
    private BigDecimal minimumPaymentStandard;
}