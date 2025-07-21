package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TServiceFacilities;
import com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities.ServiceFacilitiesListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.ServiceFacilities.TServiceFacilitiesVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ServiceFacilitiesService {
    Result<Void> add(TServiceFacilities entity);

    Result<Void> delete(Integer id);

    Result<Void> update(TServiceFacilities entity);

    Result<QueryVO<TServiceFacilitiesVO>> query(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, ServiceFacilitiesListRequest.Conditions conditions);
}
