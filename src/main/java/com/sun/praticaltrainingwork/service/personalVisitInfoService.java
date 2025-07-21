package com.sun.praticaltrainingwork.service;

import com.baomidou.mybatisplus.core.handlers.IJsonTypeHandler;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.PersonalVisitInfoVO;

public interface personalVisitInfoService {

    Result<PersonalVisitInfoVO> queryVisitsInfo(String id);
}
