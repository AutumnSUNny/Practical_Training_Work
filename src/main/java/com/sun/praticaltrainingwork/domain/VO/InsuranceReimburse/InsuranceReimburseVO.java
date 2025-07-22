package com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InsuranceReimburseVO {
    private Integer peopleId; // 人员ID
    private String hospitalGrade; // 医院等级
    private String hospitalizationNumber; // 住院号
    private String medicalCategory; // 医疗类别
    private String medicalPersonnel; // 医疗人员类别
    private BigDecimal totalAmount; // 总金额
    private BigDecimal minimumAmount; // 起付标准
    private BigDecimal reimbursementAmount; // 报销金额
    private BigDecimal personalExpenses; // 个人自费金额
    private BigDecimal amountAfterReimbursement; // 报销后金额
}