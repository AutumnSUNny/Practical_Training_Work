package com.sun.praticaltrainingwork.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("t_prescription_details")
public class TPrescriptionDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String hospitalizationNumber;

    private String chargeableItemsCategory;

    private String projectCoding;

    private String projectName;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

    private BigDecimal amount;
}