package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("personal_annual_expenses")
public class PersonalAnnualExpenses implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String peopleId;

    private Integer year;

    private Integer reimbursementTimes;

    private BigDecimal medicalExpenses;

    private BigDecimal medicareExpenses;

    private BigDecimal personalExpenses;
}
