package com.sun.praticaltrainingwork.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrescriptionDetailsAddReq {
    private String hospitalizationNumber;

    private String chargeableItemsCategory;

    private String projectCoding;

    private String projectName;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

    private BigDecimal amount;
}
