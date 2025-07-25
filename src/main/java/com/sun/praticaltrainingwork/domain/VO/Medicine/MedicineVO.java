package com.sun.praticaltrainingwork.domain.VO.Medicine;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MedicineVO {
    private Integer id;

    private String medId;

    private String medName;

    private String medExpType;

    private String medExpLevel;

    private String medMeasurement;

    private BigDecimal medMaxPrice;

    private String medApprovalmark;

    private String medHosLevel;

    private String medSize;

    private String medTradename;

    private Date medStarttime;

    private Date medEndtime;

    private String medValid;

    private String medSpecialmark;
}
