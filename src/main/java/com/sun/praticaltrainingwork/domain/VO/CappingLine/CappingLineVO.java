package com.sun.praticaltrainingwork.domain.VO.CappingLine;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class CappingLineVO {
    private Integer id;
    private String medicalPersonnelCategory;
    private BigDecimal cappingLineFee;
}