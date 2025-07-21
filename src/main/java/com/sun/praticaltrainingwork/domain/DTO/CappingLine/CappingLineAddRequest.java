package com.sun.praticaltrainingwork.domain.DTO.CappingLine;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CappingLineAddRequest {
    private String medicalPersonnelCategory;
    private BigDecimal cappingLineFee;
}