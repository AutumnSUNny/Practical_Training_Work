package com.sun.praticaltrainingwork.service;


import com.sun.praticaltrainingwork.domain.DO.TCappingLine;

import com.sun.praticaltrainingwork.domain.DTO.CappingLine.CappingLineListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.CappingLine.CappingLineVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CappingLineService {
    Result<Void> addTCappingLine(TCappingLine tCappingLine);

    Result<Void> deleteTCappingLine(Integer id);

    Result<Void> updateTCappingLine(TCappingLine tCappingLine);

    Result<QueryVO<CappingLineVO>> queryTCappingLine(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            CappingLineListRequest.Conditions conditions
    );
}