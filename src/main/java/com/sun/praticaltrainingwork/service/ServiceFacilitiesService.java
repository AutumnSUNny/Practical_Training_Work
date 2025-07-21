package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TServiceFacilities;
import com.sun.praticaltrainingwork.domain.DTO.ServiceFacilitiesListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.TServiceFacilitiesVO;

import java.util.Map;

public interface ServiceFacilitiesService {
    Result<Void> add(TServiceFacilities entity);

    Result<Void> delete(Integer id);

    Result<Void> update(TServiceFacilities entity);

    Result<QueryVO<TServiceFacilitiesVO>> query(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, ServiceFacilitiesListRequest.Conditions conditions);
}
