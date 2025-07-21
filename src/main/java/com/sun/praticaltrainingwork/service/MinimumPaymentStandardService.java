package com.sun.praticaltrainingwork.service;


import com.sun.praticaltrainingwork.domain.DO.TMinimumPaymentStandard;

import com.sun.praticaltrainingwork.domain.DTO.MinimumPaymentStandard.MinimumPaymentStandardListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.MinimumPaymentStandard.MinimumPaymentStandardVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MinimumPaymentStandardService {
    Result<Void> addTMinimumPaymentStandard(TMinimumPaymentStandard entity);

    Result<Void> deleteTMinimumPaymentStandard(Integer id);

    Result<Void> updateTMinimumPaymentStandard(TMinimumPaymentStandard entity);

    Result<QueryVO<MinimumPaymentStandardVO>> queryTMinimumPaymentStandard(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            MinimumPaymentStandardListRequest.Conditions conditions
    );
}