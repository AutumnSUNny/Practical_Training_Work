package com.sun.praticaltrainingwork.domain.DTO.MinimumPaymentStandard;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class MinimumPaymentStandardAddRequest {
    private String medicalCategory;
    private String medicalPersonnelCategory;
    private String hospitalLevel;
    private BigDecimal minimumPaymentStandard;
}