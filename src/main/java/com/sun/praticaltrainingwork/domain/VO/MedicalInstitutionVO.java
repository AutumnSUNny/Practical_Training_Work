package com.sun.praticaltrainingwork.domain.VO;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class MedicalInstitutionVO {
    private Integer id;

    private String instId;

    private String instName;

    private String instExpType;

    private String instExpLevel;

    private BigDecimal instMaxPrize;

    private Date instStarttime;

    private Date instEndtime;

    private String instValid;

    private String instHosLevel;

    private String instApprovalmark;
}
