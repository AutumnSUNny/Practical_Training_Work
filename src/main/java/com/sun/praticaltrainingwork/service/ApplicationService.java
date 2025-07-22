package com.sun.praticaltrainingwork.service;

import com.baomidou.mybatisplus.core.handlers.IJsonTypeHandler;
import com.sun.praticaltrainingwork.domain.DO.TApplicationInfo;
import com.sun.praticaltrainingwork.domain.DTO.ApplicationListRequest;
import com.sun.praticaltrainingwork.domain.DTO.People.PeopleListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.ApplicationVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ApplicationService {
    Result<Void> addApplication(TApplicationInfo application);

    Result<Void> deleteApplication(Integer id);

    Result<Void> updateApplication(TApplicationInfo application);

    Result<QueryVO<ApplicationVO>> queryApplication(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, ApplicationListRequest.Conditions conditions);
}
