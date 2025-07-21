package com.sun.praticaltrainingwork.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.praticaltrainingwork.domain.DO.People;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人员信息数据访问层
 * 继承 MyBatis-Plus 的 BaseMapper，自动获得基本 CRUD 方法
 */
@Mapper
public interface PeopleMapper extends BaseMapper<People> {

}