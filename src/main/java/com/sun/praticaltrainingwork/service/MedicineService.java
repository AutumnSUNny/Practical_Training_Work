package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TMedicine;
import com.sun.praticaltrainingwork.domain.DTO.Medicine.MedicineQueryRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.Medicine.MedicineVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MedicineService {
    Result<Void> addMedicine(TMedicine tMedicine);

    Result<Void> updateMedicine(TMedicine tMedicine);

    Result<Void> deleteMedicine(Integer id);

    Result<QueryVO<MedicineVO>> queryMedicine(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, MedicineQueryRequest.Conditions conditions);
}
