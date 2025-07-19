package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("t_individual_segement_self_funded_ratio")
public class TIndividualSegementSelfFundedRatio implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String medicalCategory;

    private String medicalPersonnelCategory;

    private String hospitalLevel;

    private BigDecimal maximumAmount;

    private BigDecimal minimumAmount;

    private BigDecimal reimbursementProportion;
}