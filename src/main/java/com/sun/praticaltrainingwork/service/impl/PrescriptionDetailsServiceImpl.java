package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TPrescriptionDetails;
import com.sun.praticaltrainingwork.domain.DTO.Prescription.PrescriptionDetailsQueryReq;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.PrescriptionDetails.TPrescriptionDetailsVO;
import com.sun.praticaltrainingwork.mapper.PrescriptionDetailsMapper;
import com.sun.praticaltrainingwork.service.PrescriptionDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrescriptionDetailsServiceImpl implements PrescriptionDetailsService {

    private final PrescriptionDetailsMapper prescriptionDetailsMapper;

    @Override
    public Result<Void> add(TPrescriptionDetails prescriptionDetails) {
        log.info("新增处方明细");
        // 自动计算金额（单价 × 数量）
        if (prescriptionDetails.getUnitPrice() != null && prescriptionDetails.getQuantity() != null) {
            BigDecimal amount = prescriptionDetails.getUnitPrice().multiply(prescriptionDetails.getQuantity());
            prescriptionDetails.setAmount(amount);
        }
        prescriptionDetailsMapper.insert(prescriptionDetails);
        return Result.success(null);
    }

    @Override
    public Result<Void> update(TPrescriptionDetails prescriptionDetails) {
        log.info("修改处方明细，ID: {}", prescriptionDetails.getId());
        // 若单价或数量变更，重新计算金额
        if (prescriptionDetails.getUnitPrice() != null || prescriptionDetails.getQuantity() != null) {
            // 查询原始数据
            TPrescriptionDetails original = prescriptionDetailsMapper.selectById(prescriptionDetails.getId());
            BigDecimal unitPrice = prescriptionDetails.getUnitPrice() != null ?
                    prescriptionDetails.getUnitPrice() : original.getUnitPrice();
            BigDecimal quantity = prescriptionDetails.getQuantity() != null ?
                    prescriptionDetails.getQuantity() : original.getQuantity();
            prescriptionDetails.setAmount(unitPrice.multiply(quantity));
        }
        prescriptionDetailsMapper.updateById(prescriptionDetails);
        return Result.success(null);
    }

    @Override
    public Result<Void> delete(Integer id) {
        log.info("删除处方明细，ID: {}", id);
        prescriptionDetailsMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<TPrescriptionDetailsVO>> query(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            PrescriptionDetailsQueryReq.Conditions conditions
    ) {
        log.debug("开始查询处方明细列表，页码: {}, 每页大小: {}", pageNum, pageSize);

        // 1. 初始化分页对象
        Page<TPrescriptionDetails> page = new Page<>(pageNum, pageSize);

        // 2. 处理排序条件
        if (sorts != null && !sorts.isEmpty()) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = entry.getKey();
                boolean isAsc = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(isAsc);
                page.addOrder(orderItem);
            }
        }

        // 3. 构建查询条件
        LambdaQueryWrapper<TPrescriptionDetails> queryWrapper = new LambdaQueryWrapper<>();

        if (conditions != null) {
            // 住院号精确查询（关联就诊记录）
            if (conditions.getHospitalizationNumber() != null) {
                queryWrapper.eq(TPrescriptionDetails::getHospitalizationNumber, conditions.getHospitalizationNumber());
            }
            // 收费项目类别精确查询
            if (conditions.getChargeableItemsCategory() != null) {
                queryWrapper.eq(TPrescriptionDetails::getChargeableItemsCategory, conditions.getChargeableItemsCategory());
            }
            // 项目编码精确查询
            if (conditions.getProjectCoding() != null) {
                queryWrapper.eq(TPrescriptionDetails::getProjectCoding, conditions.getProjectCoding());
            }
            // 项目名称模糊查询
            if (conditions.getProjectName() != null) {
                queryWrapper.like(TPrescriptionDetails::getProjectName, conditions.getProjectName());
            }
            // 单价范围查询（大于等于）
            if (conditions.getUnitPriceGte() != null) {
                queryWrapper.ge(TPrescriptionDetails::getUnitPrice, conditions.getUnitPriceGte());
            }
            // 单价范围查询（小于等于）
            if (conditions.getUnitPriceLte() != null) {
                queryWrapper.le(TPrescriptionDetails::getUnitPrice, conditions.getUnitPriceLte());
            }
            // 金额范围查询（大于等于）
            if (conditions.getAmountGte() != null) {
                queryWrapper.ge(TPrescriptionDetails::getAmount, conditions.getAmountGte());
            }
            // 金额范围查询（小于等于）
            if (conditions.getAmountLte() != null) {
                queryWrapper.le(TPrescriptionDetails::getAmount, conditions.getAmountLte());
            }
        }

        // 4. 执行分页查询
        IPage<TPrescriptionDetails> prescriptionPage = prescriptionDetailsMapper.selectPage(page, queryWrapper);

        // 5. 转换DO为VO并封装结果
        List<TPrescriptionDetailsVO> prescriptionVOList = prescriptionPage.getRecords().stream()
                .map(prescriptionDO -> {
                    TPrescriptionDetailsVO prescriptionVO = new TPrescriptionDetailsVO();
                    BeanUtils.copyProperties(prescriptionDO, prescriptionVO);
                    return prescriptionVO;
                })
                .collect(Collectors.toList());

        QueryVO<TPrescriptionDetailsVO> queryVO = new QueryVO<>();
        queryVO.setData(prescriptionVOList);
        queryVO.setTotal(prescriptionPage.getTotal());
        queryVO.setPageNum((int) prescriptionPage.getCurrent());
        queryVO.setPageSize((int) prescriptionPage.getSize());

        log.info("处方明细列表查询成功，共查询到 {} 条记录", prescriptionPage.getTotal());
        return Result.success(queryVO);
    }
}