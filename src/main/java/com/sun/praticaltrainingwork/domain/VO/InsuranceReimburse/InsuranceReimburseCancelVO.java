package com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceReimburseCancelVO {
    private Long id; // 结算记录ID
    private String hospitalizationNumber; // 住院号
    private Integer peopleId; // 人员ID
    private BigDecimal totalAmount; // 总金额
    private BigDecimal reimbursementAmount; // 报销金额
    private BigDecimal personalPay; // 个人自费
    private Integer status; // 状态：1-有效 2-已取消
    private LocalDateTime createTime; // 报销完成时间
    private LocalDateTime cancelDeadline; // 取消截止时间
    private BigDecimal minimumPaymentStandard; // 起付标准（来自配置表）
    private String hospitalGrade; // 医院等级（关联查询）
    private String medicalCategory; // 医疗类别（关联查询）
    private String medicalPersonnel; // 医疗人员类别（关联查询）
    private BigDecimal amountAfterReimbursement; // 报销后金额
}
