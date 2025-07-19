package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_medicine")
public class TMedicine implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
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
