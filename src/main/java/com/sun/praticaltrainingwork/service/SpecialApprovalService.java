package com.sun.praticaltrainingwork.service;

import com.baomidou.mybatisplus.core.handlers.IJsonTypeHandler;
import com.sun.praticaltrainingwork.domain.DO.TSpecialApproval;
import com.sun.praticaltrainingwork.domain.DTO.SpecialApprovalListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.SpecialApprovalVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SpecialApprovalService {
    Result<Void> addSpecialApproval(TSpecialApproval tSpecialApproval);

    Result<Void> deleteSpecialApproval(Integer id);

    Result<Void> updateSpecialApproval(TSpecialApproval tSpecialApproval);

    Result<QueryVO<SpecialApprovalVO>> querySpecialApproval(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, SpecialApprovalListRequest.Conditions conditions);
}
