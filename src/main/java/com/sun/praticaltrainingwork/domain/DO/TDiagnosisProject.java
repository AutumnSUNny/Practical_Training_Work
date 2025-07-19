package com.sun.praticaltrainingwork.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_diagnosis_project")
public class TDiagnosisProject implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String diaId;

    private String diaName;

    private String diaExpType;

    private String diaExpLevel;

    private BigDecimal diaMaxPrize;

    private Date diaStarttime;

    private Date diaEndtime;

    private String diaValid;

    private String diaHosLevel;

    private String diaApprovalmark;
}
