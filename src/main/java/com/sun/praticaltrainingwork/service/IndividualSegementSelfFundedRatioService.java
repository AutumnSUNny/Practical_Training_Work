package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TIndividualSegementSelfFundedRatio;

import com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IndividualSegementSelfFundedRatioService {
    Result<Void> addTIndividualSegementSelfFundedRatio(TIndividualSegementSelfFundedRatio entity);

    Result<Void> deleteTIndividualSegementSelfFundedRatio(Integer id);

    Result<Void> updateTIndividualSegementSelfFundedRatio(TIndividualSegementSelfFundedRatio entity);

    Result<QueryVO<IndividualSegementSelfFundedRatioVO>> queryTIndividualSegementSelfFundedRatio(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            IndividualSegementSelfFundedRatioListRequest.Conditions conditions
    );
}