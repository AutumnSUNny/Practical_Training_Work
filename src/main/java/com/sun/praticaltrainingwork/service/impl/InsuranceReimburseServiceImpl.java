package com.sun.praticaltrainingwork.service.impl;

import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.service.InsuranceReimburseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) // 修正注解语法

public class InsuranceReimburseServiceImpl implements InsuranceReimburseService {
    @Override
    public Result<Integer> insuranceReimburse(Integer peopleId, String hospitalGrade, String hospitalizationNumber, String medicalCategory, String medicalPersonnel) {

        return null;
    }
}
