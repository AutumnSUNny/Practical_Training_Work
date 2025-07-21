package com.sun.praticaltrainingwork.service;

import com.baomidou.mybatisplus.core.handlers.IJsonTypeHandler;
import com.sun.praticaltrainingwork.domain.Result;
import org.springframework.stereotype.Service;

@Service
public interface InsuranceReimburseService {
    Result<Integer> insuranceReimburse(Integer peopleId, String hospitalGrade, String hospitalizationNumber, String medicalCategory, String medicalPersonnel);
}
