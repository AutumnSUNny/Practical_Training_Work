package com.sun.praticaltrainingwork.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("settlement_records") // 对应数据库表名
public class SettlementRecords implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO) // 自增主键
    private Long id; // 结算记录ID

    private String hospitalizationNumber; // 住院号

    private Integer peopleId; // 人员ID

    private String hospitalGrade; // 医院等级

    private String medicalCategory; // 医疗类别

    private String medicalPersonnelCategory; // 医疗人员类别

    private BigDecimal totalAmount; // 总金额

    private BigDecimal minimumPaymentStandard; // 起付标准

    private BigDecimal reimbursementAmount; // 报销金额

    private BigDecimal personalPay; // 个人自费

    private BigDecimal amountAfterReimbursement; // 报销后金额

    private Integer status; // 状态：1-有效 2-已取消 3-已支付

    private LocalDateTime createTime; // 报销完成时间

    private LocalDateTime cancelDeadline; // 取消截止时间

    // 注：已移除operator字段（按需求无需操作人）
}