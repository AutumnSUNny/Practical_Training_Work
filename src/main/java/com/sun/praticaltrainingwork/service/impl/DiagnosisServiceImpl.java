package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TDiagnosisProject;
import com.sun.praticaltrainingwork.domain.DTO.DiagnosisListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.TDiagnosisProjectVO;
import com.sun.praticaltrainingwork.mapper.DiagnosisMapper;
import com.sun.praticaltrainingwork.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisMapper diagnosisMapper;

    @Override
    public Result<Void> addDiagnosis(TDiagnosisProject tdiagnosisproject) {
        log.info("添加诊疗项目");
        diagnosisMapper.insert(tdiagnosisproject);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteDiagnosis(Integer id) {
        log.info("删除诊疗项目");
        diagnosisMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateDiagnosis(TDiagnosisProject tdiagnosisproject) {
        log.info("更新诊疗项目");
        diagnosisMapper.updateById(tdiagnosisproject);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<TDiagnosisProjectVO>> queryDiagnosis(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, DiagnosisListRequest.Conditions conditions) {
        log.debug("开始查询诊疗列表，页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<TDiagnosisProject> page = new Page<>(pageNum, pageSize);
        //根据排序字段依次排序
        if (sorts != null) {
            //按排序字段顺序优先级排序,降序为true,升序为false
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = entry.getKey();
                boolean isDesc = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(!isDesc);
                page.addOrder(orderItem);
            }
        }

        IPage<TDiagnosisProject> userPage = diagnosisMapper.selectPage(page,new LambdaQueryWrapper<TDiagnosisProject>()
                .like(conditions.getDiaId() != null, TDiagnosisProject::getDiaId, conditions.getDiaId())
                .like(conditions.getDiaName() != null, TDiagnosisProject::getDiaName, conditions.getDiaName())
                .eq(conditions.getDiaExpType()!= null, TDiagnosisProject::getDiaExpType, conditions.getDiaExpType())
                .eq(conditions.getDiaExpLevel()!= null, TDiagnosisProject::getDiaExpLevel, conditions.getDiaExpLevel())
                .like(conditions.getDiaMaxPrize() != null, TDiagnosisProject::getDiaMaxPrize, conditions.getDiaMaxPrize())
                .eq(conditions.getDiaValid() != null, TDiagnosisProject::getDiaValid, conditions.getDiaValid())
                .eq(conditions.getDiaHosLevel()!= null, TDiagnosisProject::getDiaHosLevel, conditions.getDiaHosLevel())
                .between(conditions.getDiaStarttime() != null && conditions.getDiaEndtime() != null
                        , TDiagnosisProject::getDiaStarttime, conditions.getDiaStarttime(), conditions.getDiaEndtime()));



        QueryVO<TDiagnosisProjectVO> queryVO = new QueryVO<>();
        queryVO.setData(userPage.getRecords().stream().map(user -> {
            TDiagnosisProjectVO projectVO = new TDiagnosisProjectVO();
            BeanUtils.copyProperties(user, projectVO);
            return projectVO;
        }).toList());
        queryVO.setTotal(userPage.getTotal());
        queryVO.setPageNum((int) userPage.getCurrent());
        queryVO.setPageSize((int) userPage.getSize());

        log.info("诊疗列表查询成功，共查询到 {} 条记录", userPage.getTotal());
        return Result.success(queryVO);


    }
}
