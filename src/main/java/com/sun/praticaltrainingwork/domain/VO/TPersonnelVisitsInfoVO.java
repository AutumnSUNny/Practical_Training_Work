package com.sun.praticaltrainingwork.domain.VO;

import lombok.Data;

import java.util.Date;

@Data
public class TPersonnelVisitsInfoVO {
    private Integer id;

    private String personID;

    private String hospitalizationNumber;

    private String designatedNumber;

    private String medicalCategory;

    private Date admissionDate;

    private Date dischargeDate;

    private String diseaseCode;

    private String hospitalGrade;

    private String admissionCode;

    private String diagnosedName;

    private String dischargeReason;

    private String settlementFlag;
}
