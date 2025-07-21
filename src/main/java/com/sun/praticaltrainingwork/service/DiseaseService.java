package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TDisease;
import com.sun.praticaltrainingwork.domain.DTO.Disease.DiseaseListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.Disease.TDiseaseVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DiseaseService {
    Result<Void> addDisease(TDisease disease);

    Result<Void> deleteDisease(Integer id);

    Result<Void> updateDisease(TDisease disease);

    Result<QueryVO<TDiseaseVO>> queryDisease(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, DiseaseListRequest.Conditions conditions);
}
