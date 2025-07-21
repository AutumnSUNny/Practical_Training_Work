package com.sun.praticaltrainingwork.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TMinimumPaymentStandard;

import com.sun.praticaltrainingwork.domain.DTO.MinimumPaymentStandard.MinimumPaymentStandardListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.MinimumPaymentStandard.MinimumPaymentStandardVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import com.sun.praticaltrainingwork.mapper.MinimumPaymentStandardMapper;
import com.sun.praticaltrainingwork.service.MinimumPaymentStandardService;
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
public class MinimumPaymentStandardServiceImpl implements MinimumPaymentStandardService {

    private final MinimumPaymentStandardMapper minimumPaymentStandardMapper;

    @Override
    public Result<Void> addTMinimumPaymentStandard(TMinimumPaymentStandard entity) {
        log.info("添加起付标准数据");
        minimumPaymentStandardMapper.insert(entity);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteTMinimumPaymentStandard(Integer id) {
        log.info("删除起付标准数据，ID: {}", id);
        minimumPaymentStandardMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateTMinimumPaymentStandard(TMinimumPaymentStandard entity) {
        log.info("更新起付标准数据，ID: {}", entity.getId());
        minimumPaymentStandardMapper.updateById(entity);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<MinimumPaymentStandardVO>> queryTMinimumPaymentStandard(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            MinimumPaymentStandardListRequest.Conditions conditions
    ) {
        log.debug("开始查询起付标准列表，页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<TMinimumPaymentStandard> page = new Page<>(pageNum, pageSize);

        // 设置排序规则
        if (sorts != null) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = CommonUtils.camelToUnderline( entry.getKey());
                boolean isDesc = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(!isDesc);
                page.addOrder(orderItem);
            }
        }

        // 构建查询条件
        IPage<TMinimumPaymentStandard> entityPage = minimumPaymentStandardMapper.selectPage(
                page,
                new LambdaQueryWrapper<TMinimumPaymentStandard>()
                        .eq(conditions.getMedicalCategory() != null,
                                TMinimumPaymentStandard::getMedicalCategory, conditions.getMedicalCategory())
                        .eq(conditions.getMedicalPersonnelCategory() != null,
                                TMinimumPaymentStandard::getMedicalPersonnelCategory, conditions.getMedicalPersonnelCategory())
                        .eq(conditions.getHospitalLevel() != null,
                                TMinimumPaymentStandard::getHospitalLevel, conditions.getHospitalLevel())
                        .eq(conditions.getMinimumPaymentStandard() != null,
                                TMinimumPaymentStandard::getMinimumPaymentStandard, conditions.getMinimumPaymentStandard())
        );

        // 转换结果
        QueryVO<MinimumPaymentStandardVO> queryVO = new QueryVO<>();
        queryVO.setData(entityPage.getRecords().stream().map(entity -> {
            MinimumPaymentStandardVO vo = new MinimumPaymentStandardVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).toList());
        queryVO.setTotal(entityPage.getTotal());
        queryVO.setPageNum((int) entityPage.getCurrent());
        queryVO.setPageSize((int) entityPage.getSize());

        log.info("起付标准列表查询成功，共查询到 {} 条记录", entityPage.getTotal());
        return Result.success(queryVO);
    }
}
