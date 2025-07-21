package com.sun.praticaltrainingwork.domain.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class PersonalVisitInfoVO {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
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
