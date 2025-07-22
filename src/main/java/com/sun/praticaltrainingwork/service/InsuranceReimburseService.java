package com.sun.praticaltrainingwork.service;

import com.baomidou.mybatisplus.core.handlers.IJsonTypeHandler;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse.InsuranceReimburseVO;
import org.springframework.stereotype.Service;

@Service
public interface InsuranceReimburseService {
    Result<InsuranceReimburseVO> insuranceReimburse(Integer peopleId, String hospitalGrade, String hospitalizationNumber, String medicalCategory, String medicalPersonnel);
}
