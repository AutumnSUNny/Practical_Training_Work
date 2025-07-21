package com.sun.praticaltrainingwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.praticaltrainingwork.domain.DO.TDiagnosisProject;
import com.sun.praticaltrainingwork.domain.DO.TMedicine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicineMapper extends BaseMapper<TMedicine> {
}
