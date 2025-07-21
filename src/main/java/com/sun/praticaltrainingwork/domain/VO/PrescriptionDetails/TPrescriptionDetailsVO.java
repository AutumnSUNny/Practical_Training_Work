package com.sun.praticaltrainingwork.domain.VO.PrescriptionDetails;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TPrescriptionDetailsVO {
    private Integer id;

    private String hospitalizationNumber;

    private String chargeableItemsCategory;

    private String projectCoding;

    private String projectName;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

    private BigDecimal amount;
}
