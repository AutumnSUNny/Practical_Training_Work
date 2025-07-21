package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.People;
import com.sun.praticaltrainingwork.domain.DTO.People.PeopleListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.People.PeopleVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import java.util.Map;

public interface PeopleService {
    Result<Object> queryPeopleAndUnits(String id);

    Result<Void> addPeople(People people);

    Result<Void> deletePeople(Integer id);

    Result<Void> updatePeople(People people);

    Result<QueryVO<PeopleVO>> queryPeople(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            PeopleListRequest.Conditions conditions
    );

}
