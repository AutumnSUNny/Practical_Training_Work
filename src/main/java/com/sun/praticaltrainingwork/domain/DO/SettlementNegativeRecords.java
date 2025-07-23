package com.sun.praticaltrainingwork.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("settlement_negative_records") // 对应数据库表名
public class SettlementNegativeRecords implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO) // 自增主键
    private Long id; // 负记录ID

    private String hospitalizationNumber;

    private BigDecimal negativeAmount; // 负记录金额（与原报销金额绝对值相等、符号相反）

    private LocalDateTime createTime; // 负记录创建时间

    private String remark; // 取消原因
}