package com.sun.praticaltrainingwork.service.impl;

import com.sun.praticaltrainingwork.domain.DO.TPersonnelVisitsInfo;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.PersonalVisitInfoVO;
import com.sun.praticaltrainingwork.mapper.personalVisitInfoMapper;
import com.sun.praticaltrainingwork.service.personalVisitInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class personalVisitInfoServiceImpl implements personalVisitInfoService {

    private personalVisitInfoMapper personalVisitInfoMapper;


    @Override
    public Result<PersonalVisitInfoVO> queryVisitsInfo(String id) {
        // 根据id查询
        TPersonnelVisitsInfo personalVisitInfo = new TPersonnelVisitsInfo();
        personalVisitInfo = personalVisitInfoMapper.selectById(id);
        // 转换为VO
        PersonalVisitInfoVO personalVisitInfoVO = new PersonalVisitInfoVO();
        BeanUtils.copyProperties(personalVisitInfo, personalVisitInfoVO);
        return Result.success(personalVisitInfoVO);
    }
}
