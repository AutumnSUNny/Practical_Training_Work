package com.sun.praticaltrainingwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.praticaltrainingwork.domain.DO.TApplicationInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationMapper extends BaseMapper<TApplicationInfo> {
}
