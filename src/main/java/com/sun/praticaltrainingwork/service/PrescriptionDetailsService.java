package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TPrescriptionDetails;
import com.sun.praticaltrainingwork.domain.DTO.Prescription.PrescriptionDetailsQueryReq;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.PrescriptionDetails.TPrescriptionDetailsVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PrescriptionDetailsService {

    /**
     * 新增处方明细
     */
    Result<Void> add(TPrescriptionDetails prescriptionDetails);

    /**
     * 修改处方明细
     */
    Result<Void> update(TPrescriptionDetails prescriptionDetails);

    /**
     * 删除处方明细
     */
    Result<Void> delete(Integer id);

    /**
     * 分页查询处方明细
     */
    Result<QueryVO<TPrescriptionDetailsVO>> query(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            PrescriptionDetailsQueryReq.Conditions conditions
    );
}