package com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 医保报销请求DTO
 */
@Data
public class InsuranceReimburseReq {
    private String hospitalizationNumber; // 住院号
    private Integer peopleId; // 人员ID
    private String hospitalGrade; // 医院等级
    private String medicalCategory; // 医疗类别
    private String medicalPersonnel; // 医疗人员类别
}