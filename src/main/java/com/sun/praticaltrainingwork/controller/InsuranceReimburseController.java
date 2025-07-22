package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse.InsuranceReimburseReq;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse.InsuranceReimburseVO;
import com.sun.praticaltrainingwork.domain.VO.Restful;
import com.sun.praticaltrainingwork.service.InsuranceReimburseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="医保报销管理")
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) // 修正注解语法
public class InsuranceReimburseController {
    private final InsuranceReimburseService insuranceReimburseService;

    @Operation(summary = "计算报销后结果",description = "计算报销后结果")
    @PostMapping
    public Restful.ResultJson InsuranceReimburse(InsuranceReimburseReq req) {
        return insuranceReimburseService.insuranceReimburse(req.getPeopleId(),req.getHospitalGrade(),
                req.getHospitalizationNumber(),req.getMedicalCategory(),
                req.getMedicalPersonnel()).toJson();
    }
}
