package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TDisease;
import com.sun.praticaltrainingwork.domain.DTO.Disease.DiseaseListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.Disease.TDiseaseVO;
import com.sun.praticaltrainingwork.mapper.DiseaseMapper;
import com.sun.praticaltrainingwork.service.DiseaseService;
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
public class DiseaseServiceImpl implements DiseaseService {

    private final DiseaseMapper diseaseMapper;

    @Override
    public Result<Void> addDisease(TDisease disease) {
        log.info("添加疾病");
        diseaseMapper.insert(disease);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteDisease(Integer id) {
        log.info("删除疾病");
        diseaseMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateDisease(TDisease disease) {
        log.info("更新疾病");
        diseaseMapper.updateById(disease);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<TDiseaseVO>> queryDisease(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, DiseaseListRequest.Conditions conditions) {
        log.debug("查询疾病列表，页码: {}, 每页: {}", pageNum, pageSize);
        Page<TDisease> page = new Page<>(pageNum, pageSize);

        if (sorts != null) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = CommonUtils.camelToUnderline( entry.getKey());
                boolean isDesc = entry.getValue();

                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(!isDesc); // true 表示升序，false 表示降序

                page.addOrder(orderItem);
            }
        }

        IPage<TDisease> resultPage = diseaseMapper.selectPage(page, new LambdaQueryWrapper<TDisease>()
                .like(conditions.getDiseaseId() != null, TDisease::getDiseaseId, conditions.getDiseaseId())
                .like(conditions.getDiseaseName() != null, TDisease::getDiseaseName, conditions.getDiseaseName())
                .eq(conditions.getDiseaseType() != null, TDisease::getDiseaseType, conditions.getDiseaseType())
                .like(conditions.getDiseaseReimbursementStandards() != null, TDisease::getDiseaseReimbursementStandards, conditions.getDiseaseReimbursementStandards())
                .like(conditions.getNotes() != null, TDisease::getNotes, conditions.getNotes())
        );

        QueryVO<TDiseaseVO> queryVO = new QueryVO<>();
        queryVO.setData(resultPage.getRecords().stream().map(d -> {
            TDiseaseVO vo = new TDiseaseVO();
            BeanUtils.copyProperties(d, vo);
            return vo;
        }).toList());
        queryVO.setTotal(resultPage.getTotal());
        queryVO.setPageNum((int) resultPage.getCurrent());
        queryVO.setPageSize((int) resultPage.getSize());

        return Result.success(queryVO);
    }
}
