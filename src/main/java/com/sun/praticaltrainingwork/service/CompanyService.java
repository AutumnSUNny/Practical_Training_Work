package com.sun.praticaltrainingwork.service;


import com.sun.praticaltrainingwork.domain.DO.Company;

import com.sun.praticaltrainingwork.domain.DTO.Company.CompanyListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.Company.CompanyVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public interface CompanyService {
    Result<Void> addCompany(Company company);

    Result<Void> deleteCompany(Integer id);

    Result<Void> updateCompany(Company company);

    Result<QueryVO<CompanyVO>> queryCompany(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            CompanyListRequest.Conditions conditions
    );
}