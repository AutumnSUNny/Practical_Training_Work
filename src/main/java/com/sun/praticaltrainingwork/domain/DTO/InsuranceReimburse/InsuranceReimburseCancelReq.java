package com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse;
import lombok.Data;

@Data
public class InsuranceReimburseCancelReq {
    private String peopleId; // 结算记录ID
    private String hospitalizationNumber; // 住院号
}
