package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TPersonnelVisitsInfo;
import com.sun.praticaltrainingwork.domain.DTO.PersonnelVisitsInfo.PersonnelVisitsInfoQueryReq;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.PersonnelVisitsInfo.TPersonnelVisitsInfoVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service

public interface PersonnelVisitsInfoService {
    Result<Void> add(TPersonnelVisitsInfo tpersonnelVisitInfo);

    Result<Void> update(TPersonnelVisitsInfo tPersonnelVisitsInfo);

    Result<Void> delete(Integer id);

    Result<QueryVO<TPersonnelVisitsInfoVO>> query(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, PersonnelVisitsInfoQueryReq.Conditions conditions);
}
