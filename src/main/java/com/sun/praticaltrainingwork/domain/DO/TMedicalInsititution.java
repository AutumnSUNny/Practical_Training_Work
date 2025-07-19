package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_medical_insititution")
public class TMedicalInsititution implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
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