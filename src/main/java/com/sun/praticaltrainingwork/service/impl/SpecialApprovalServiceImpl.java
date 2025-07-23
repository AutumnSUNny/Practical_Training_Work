package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TSpecialApproval;
import com.sun.praticaltrainingwork.domain.DTO.SpecialApprovalListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.SpecialApprovalVO;
import com.sun.praticaltrainingwork.mapper.SpecialApprovalMapper;
import com.sun.praticaltrainingwork.service.SpecialApprovalService;
import com.sun.praticaltrainingwork.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class SpecialApprovalServiceImpl implements SpecialApprovalService {
    private final SpecialApprovalMapper specialApprovalMapper;
    @Override
    public Result<Void> addSpecialApproval(TSpecialApproval tSpecialApproval) {
        try {
            specialApprovalMapper.insert(tSpecialApproval);
            return Result.success(null);
        } catch (Exception e) {
            log.error("添加特殊审批失败", e);
            return Result.failure(new RuntimeException("添加特殊审批失败", e));
        }
    }

    @Override
    public Result<Void> deleteSpecialApproval(Integer id) {
        try {
            specialApprovalMapper.deleteById(id);
            return Result.success(null);
        } catch (Exception e) {
            log.error("删除特殊审批失败", e);
            return Result.failure(new RuntimeException("删除特殊审批失败", e));
    }
}

    @Override
    public Result<Void> updateSpecialApproval(TSpecialApproval tSpecialApproval) {
        try {
            specialApprovalMapper.updateById(tSpecialApproval);
            return Result.success(null);
        } catch (Exception e) {
            log.error("更新特殊审批失败", e);
            return Result.failure(new RuntimeException("更新特殊审批失败", e));
        }
    }

    @Override
    public Result<QueryVO<SpecialApprovalVO>> querySpecialApproval(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, SpecialApprovalListRequest.Conditions conditions) {

        log.debug("特殊审批请求列表, 页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<TSpecialApproval> page = new Page<>(pageNum, pageSize);

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
        LambdaQueryWrapper<TSpecialApproval> queryWrapper = new LambdaQueryWrapper<>();
        // 模糊查询（文本类字段）
        queryWrapper.like(conditions.getPersonID() != null, TSpecialApproval::getPersonId, conditions.getPersonID())
                .like(conditions.getApprovalNumber() != null, TSpecialApproval::getApprovalNumber, conditions.getApprovalNumber())
                .like(conditions.getDrugCode() != null, TSpecialApproval::getDrugCode, conditions.getDrugCode())
                .like(conditions.getApprovalOpinions() != null, TSpecialApproval::getApprovalOpinions, conditions.getApprovalOpinions());
        // 精确查询（分类类字段，0/1/2等）
        queryWrapper
                .eq(conditions.getApprovalCategory() != null, TSpecialApproval::getApprovalCategory, conditions.getApprovalCategory())
                .eq(conditions.getTerminationDate() != null, TSpecialApproval::getTerminationDate, conditions.getTerminationDate())
                .eq(conditions.getApprover() != null, TSpecialApproval::getApprover, conditions.getApprover())
                .eq(conditions.getApprovalFlag() != null, TSpecialApproval::getApprovalFlag, conditions.getApprovalFlag())
                .eq(conditions.getApprovalDate() != null, TSpecialApproval::getApprovalDate, conditions.getApprovalDate());


        // 日期范围查询
        queryWrapper.between(conditions.getStartDate() != null, TSpecialApproval::getStartDate, conditions.getStartDate(), conditions.getTerminationDate());
        // 执行分页查询

        IPage<TSpecialApproval> specialApprovalIPage = specialApprovalMapper.selectPage(page, queryWrapper);

        // 转换为VO
        QueryVO<SpecialApprovalVO> queryVO = new QueryVO<>();
        queryVO.setData(specialApprovalIPage.getRecords().stream().map(specialApproval -> {
            SpecialApprovalVO specialApprovalVO = new SpecialApprovalVO();
            BeanUtils.copyProperties(specialApproval, specialApprovalVO);
            return specialApprovalVO;
        }).toList());
        queryVO.setTotal(specialApprovalIPage.getTotal());
        queryVO.setPageNum((int) specialApprovalIPage.getCurrent());
        queryVO.setPageSize((int) specialApprovalIPage.getSize());

        log.info("特殊审批查询成功, 共{}条记录", specialApprovalIPage.getTotal());
        return Result.success(queryVO);
    }
}
