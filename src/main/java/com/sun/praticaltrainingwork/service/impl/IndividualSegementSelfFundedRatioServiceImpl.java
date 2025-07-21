package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TIndividualSegementSelfFundedRatio;

import com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import com.sun.praticaltrainingwork.mapper.IndividualSegementSelfFundedRatioMapper;
import com.sun.praticaltrainingwork.service.IndividualSegementSelfFundedRatioService;
import com.sun.praticaltrainingwork.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IndividualSegementSelfFundedRatioServiceImpl implements IndividualSegementSelfFundedRatioService {

    private final IndividualSegementSelfFundedRatioMapper individualSegementSelfFundedRatioMapper;

    @Override
    public Result<Void> addTIndividualSegementSelfFundedRatio(TIndividualSegementSelfFundedRatio entity) {
        log.info("添加个人分段自费比例数据");
        individualSegementSelfFundedRatioMapper.insert(entity);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteTIndividualSegementSelfFundedRatio(Integer id) {
        log.info("删除个人分段自费比例数据，ID: {}", id);
        individualSegementSelfFundedRatioMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateTIndividualSegementSelfFundedRatio(TIndividualSegementSelfFundedRatio entity) {
        log.info("更新个人分段自费比例数据，ID: {}", entity.getId());
        individualSegementSelfFundedRatioMapper.updateById(entity);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<IndividualSegementSelfFundedRatioVO>> queryTIndividualSegementSelfFundedRatio(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            IndividualSegementSelfFundedRatioListRequest.Conditions conditions
    ) {
        log.debug("开始查询个人分段自费比例列表，页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<TIndividualSegementSelfFundedRatio> page = new Page<>(pageNum, pageSize);

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
        LambdaQueryWrapper<TIndividualSegementSelfFundedRatio> queryWrapper = new LambdaQueryWrapper<>();

        if (conditions.getMedicalCategory() != null) {
            queryWrapper.eq(TIndividualSegementSelfFundedRatio::getMedicalCategory, conditions.getMedicalCategory());
        }

        if (conditions.getMedicalPersonnelCategory() != null) {
            queryWrapper.eq(TIndividualSegementSelfFundedRatio::getMedicalPersonnelCategory, conditions.getMedicalPersonnelCategory());
        }

        if (conditions.getHospitalLevel() != null) {
            queryWrapper.eq(TIndividualSegementSelfFundedRatio::getHospitalLevel, conditions.getHospitalLevel());
        }

        if (conditions.getMaximumAmount() != null) {
            queryWrapper.ge(TIndividualSegementSelfFundedRatio::getMaximumAmount, conditions.getMaximumAmount());
        }

        if (conditions.getMinimumAmount() != null) {
            queryWrapper.le(TIndividualSegementSelfFundedRatio::getMinimumAmount, conditions.getMinimumAmount());
        }

        if (conditions.getReimbursementProportion() != null) {
            queryWrapper.eq(TIndividualSegementSelfFundedRatio::getReimbursementProportion, conditions.getReimbursementProportion());
        }

        // 执行查询
        IPage<TIndividualSegementSelfFundedRatio> entityPage = individualSegementSelfFundedRatioMapper.selectPage(page, queryWrapper);

        // 转换结果
        QueryVO<IndividualSegementSelfFundedRatioVO> queryVO = new QueryVO<>();
        queryVO.setData(entityPage.getRecords().stream().map(entity -> {
            IndividualSegementSelfFundedRatioVO vo = new IndividualSegementSelfFundedRatioVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).toList());
        queryVO.setTotal(entityPage.getTotal());
        queryVO.setPageNum((int) entityPage.getCurrent());
        queryVO.setPageSize((int) entityPage.getSize());

        log.info("个人分段自费比例列表查询成功，共查询到 {} 条记录", entityPage.getTotal());
        return Result.success(queryVO);
    }
}