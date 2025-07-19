package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_personnel_visits_info")
public class TPersonnelVisitsInfo implements Serializable {
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
