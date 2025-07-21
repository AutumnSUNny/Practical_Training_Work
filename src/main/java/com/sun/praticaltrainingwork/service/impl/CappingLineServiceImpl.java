package com.sun.praticaltrainingwork.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TCappingLine;

import com.sun.praticaltrainingwork.domain.DTO.CappingLine.CappingLineListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.CappingLine.CappingLineVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;


import com.sun.praticaltrainingwork.mapper.CappingLineMapper;
import com.sun.praticaltrainingwork.service.CappingLineService;
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
public class CappingLineServiceImpl implements CappingLineService {

    private final CappingLineMapper cappingLineMapper;

    @Override
    public Result<Void> addTCappingLine(TCappingLine tCappingLine) {
        log.info("添加封顶线数据");
        cappingLineMapper.insert(tCappingLine);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteTCappingLine(Integer id) {
        log.info("删除封顶线数据，ID: {}", id);
        cappingLineMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateTCappingLine(TCappingLine tCappingLine) {
        log.info("更新封顶线数据，ID: {}", tCappingLine.getId());
        cappingLineMapper.updateById(tCappingLine);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<CappingLineVO>> queryTCappingLine(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            CappingLineListRequest.Conditions conditions
    ) {
        log.debug("开始查询封顶线列表，页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<TCappingLine> page = new Page<>(pageNum, pageSize);

        // 设置排序规则
        if (sorts != null) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = CommonUtils.camelToUnderline(entry.getKey()) ;
                boolean isDesc = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(!isDesc);
                page.addOrder(orderItem);
            }
        }

        // 构建查询条件
        IPage<TCappingLine> cappingLinePage = cappingLineMapper.selectPage(
                page,
                new LambdaQueryWrapper<TCappingLine>()
                        .eq(conditions.getMedicalPersonnelCategory() != null,
                                TCappingLine::getMedicalPersonnelCategory, conditions.getMedicalPersonnelCategory())
                        .eq(conditions.getCappingLineFee() != null,
                                TCappingLine::getCappingLineFee, conditions.getCappingLineFee())
        );

        // 转换结果
        QueryVO<CappingLineVO> queryVO = new QueryVO<>();
        queryVO.setData(cappingLinePage.getRecords().stream().map(cappingLine -> {
            CappingLineVO vo = new CappingLineVO();
            BeanUtils.copyProperties(cappingLine, vo);
            return vo;
        }).toList());
        queryVO.setTotal(cappingLinePage.getTotal());
        queryVO.setPageNum((int) cappingLinePage.getCurrent());
        queryVO.setPageSize((int) cappingLinePage.getSize());

        log.info("封顶线列表查询成功，共查询到 {} 条记录", cappingLinePage.getTotal());
        return Result.success(queryVO);
    }
}