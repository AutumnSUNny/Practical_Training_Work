package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TDiagnosisProject;
import com.sun.praticaltrainingwork.domain.DTO.DiagnosisListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.TDiagnosisProjectVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DiagnosisService {
    Result<Void> addDiagnosis(TDiagnosisProject tdiagnosisproject);

    Result<Void> deleteDiagnosis(Integer id);

    Result<Void> updateDiagnosis(TDiagnosisProject tdiagnosisproject);

    Result<QueryVO<TDiagnosisProjectVO>> queryDiagnosis(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, DiagnosisListRequest.Conditions conditions);
}
