package com.sun.praticaltrainingwork.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.praticaltrainingwork.domain.DO.SettlementRecords;
import com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse.InsuranceReimburseQueryRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse.InsuranceReimburseVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

public interface InsuranceReimburseService extends IService<SettlementRecords> {
    // 核心方法参数改为DO
    Result<InsuranceReimburseVO> calculateAndSaveReimburse(SettlementRecords record);
    Result<InsuranceReimburseVO> queryReimburseByHospitalNo(String hospitalizationNumber);
    Result<String> cancelReimburse(SettlementRecords record);
    Result<String> confirmPayment(String hospitalizationNumber);
    // 新增：分页查询所有报销记录

    Result<QueryVO<InsuranceReimburseVO>> queryReimburseByPage(InsuranceReimburseQueryRequest request);
}