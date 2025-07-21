package com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse;

import lombok.Data;

import java.security.PrivateKey;

@Data
public class InsuranceReimburseReq {
    private Integer peopleId;  //人员id
    private String medicalPersonnel; //医疗人员类别
    private String medicalCategory;  //医疗类别
    private String hospitalGrade;  //医院等级
    private String hospitalizationNumber; //住院号
}
