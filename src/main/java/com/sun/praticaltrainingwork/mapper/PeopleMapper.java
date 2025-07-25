package com.sun.praticaltrainingwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.praticaltrainingwork.domain.DO.People;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeopleMapper extends BaseMapper<People> {
    Object queryPeopleAndUnits(String id);
}
