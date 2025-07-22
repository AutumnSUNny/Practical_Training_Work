package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse.*;
import com.sun.praticaltrainingwork.domain.DO.SettlementRecords;
import com.sun.praticaltrainingwork.domain.VO.Restful;
import com.sun.praticaltrainingwork.service.InsuranceReimburseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name="医保报销管理")
@Slf4j
@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceReimburseController {
    private final InsuranceReimburseService insuranceReimburseService;

    // 1. 计算报销并保存
    @Operation(summary = "计算并保存报销结果")
    @PostMapping("/calculate")
    public Restful.ResultJson calculateAndSave(@RequestBody InsuranceReimburseReq req) {
        SettlementRecords record = new SettlementRecords();
        BeanUtils.copyProperties(req, record);
        // 特殊处理：将req中的medicalPersonnel映射到DO的medicalPersonnelCategory
        record.setMedicalPersonnelCategory(req.getMedicalPersonnel());
        return insuranceReimburseService.calculateAndSaveReimburse(record).toJson();
    }

    // 2. 查询报销记录
    @Operation(summary = "查询报销记录")
    @GetMapping("/query/{hospitalizationNumber}")
    public Restful.ResultJson queryByHospitalNo(@PathVariable String hospitalizationNumber) {
        return insuranceReimburseService.queryReimburseByHospitalNo(hospitalizationNumber).toJson();
    }

    // 3. 取消报销
    @Operation(summary = "取消报销")
    @PostMapping("/cancel")
    public Restful.ResultJson cancelReimburse(@RequestBody InsuranceReimburseCancelReq req) {
        SettlementRecords record = new SettlementRecords();
        BeanUtils.copyProperties(req, record);
        return insuranceReimburseService.cancelReimburse(record).toJson();
    }

    // 4. 确认支付
    @Operation(summary = "确认支付")
    @PostMapping("/pay")
    public Restful.ResultJson confirmPayment(@RequestBody InsuranceReimbursePayReq req) {
        return insuranceReimburseService.confirmPayment(req.getHospitalizationNumber()).toJson();
    }

    @Operation(summary = "分页查询报销记录（支持多条件筛选和排序）")
    @PostMapping("/query")
    public Restful.ResultJson queryByPage(@RequestBody InsuranceReimburseQueryRequest request) {
        return insuranceReimburseService.queryReimburseByPage(request).toJson();
    }
}