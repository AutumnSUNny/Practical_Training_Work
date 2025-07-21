package com.sun.praticaltrainingwork.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.Company;

import com.sun.praticaltrainingwork.domain.DTO.Company.CompanyListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.Company.CompanyVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import com.sun.praticaltrainingwork.mapper.CompanyMapper;
import com.sun.praticaltrainingwork.service.CompanyService;
import com.sun.praticaltrainingwork.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Override
    public Result<Void> addCompany(Company company) {
        log.info("添加单位信息: {}", company.getCompanyName());
        companyMapper.insert(company);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteCompany(Integer id) {
        log.info("删除单位信息, ID: {}", id);
        companyMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateCompany(Company company) {
        log.info("修改单位信息, ID: {}", company.getId());
        companyMapper.updateById(company);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<CompanyVO>> queryCompany(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            CompanyListRequest.Conditions conditions
    ) {
        log.debug("查询单位列表, 页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<Company> page = new Page<>(pageNum, pageSize);

        // 处理排序
        if (sorts != null) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = CommonUtils.camelToUnderline(entry.getKey());
                boolean isDesc = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(!isDesc);
                page.addOrder(orderItem);
            }
        }

        // 构建查询条件
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper<>();
        // 模糊查询（文本类字段）
        queryWrapper.like(conditions.getCompanyId() != null, Company::getCompanyId, conditions.getCompanyId())
                .like(conditions.getCompanyName() != null, Company::getCompanyName, conditions.getCompanyName())
                .like(conditions.getAddress() != null, Company::getAddress, conditions.getAddress())
                .like(conditions.getPostcode() != null, Company::getPostcode, conditions.getPostcode())
                .like(conditions.getPhoneNumber() != null, Company::getPhoneNumber, conditions.getPhoneNumber());
        // 精确查询（分类字段：单位类型）
        queryWrapper.eq(conditions.getCompanyType() != null, Company::getCompanyType, conditions.getCompanyType());

        // 执行分页查询
        IPage<Company> companyPage = companyMapper.selectPage(page, queryWrapper);

        // 转换为VO
        QueryVO<CompanyVO> queryVO = new QueryVO<>();
        queryVO.setData(companyPage.getRecords().stream().map(company -> {
            CompanyVO companyVO = new CompanyVO();
            BeanUtils.copyProperties(company, companyVO);
            return companyVO;
        }).toList());
        queryVO.setTotal(companyPage.getTotal());
        queryVO.setPageNum((int) companyPage.getCurrent());
        queryVO.setPageSize((int) companyPage.getSize());

        log.info("单位列表查询成功, 共{}条记录", companyPage.getTotal());
        return Result.success(queryVO);
    }
}
