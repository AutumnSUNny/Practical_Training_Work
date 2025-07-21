package com.sun.praticaltrainingwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.praticaltrainingwork.domain.DO.Company;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单位信息数据访问层
 * 继承MyBatis-Plus的BaseMapper，自动获得基本CRUD方法
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
    // 继承BaseMapper后无需手动编写基本方法，包含：
    // insert(Company entity)、deleteById(Serializable id)、updateById(Company entity)、selectPage(...)等
}