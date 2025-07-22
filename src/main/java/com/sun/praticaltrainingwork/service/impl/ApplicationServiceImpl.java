package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TApplicationInfo;
import com.sun.praticaltrainingwork.domain.DTO.ApplicationListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.ApplicationVO;
import com.sun.praticaltrainingwork.domain.VO.People.PeopleVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.mapper.ApplicationMapper;
import com.sun.praticaltrainingwork.service.ApplicationService;
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
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationMapper applicationMapper;

    @Override
    public Result<Void> addApplication(TApplicationInfo application) {

        try {
            applicationMapper.insert(application);
            return Result.success(null);
        } catch (Exception e) {
            log.error("添加定点请求失败", e);
            return Result.failure(new RuntimeException("添加定点请求失败", e));
        }
    }

    @Override
    public Result<Void> deleteApplication(Integer id) {

        try {
            applicationMapper.deleteById(id);
            return Result.success(null);
        } catch (Exception e) {
            log.error("删除定点请求失败", e);
            return Result.failure(new RuntimeException("删除定点请求失败", e));
        }
    }

    @Override
    public Result<Void> updateApplication(TApplicationInfo application) {

        try {
            applicationMapper.updateById(application);
            return Result.success(null);
        } catch (Exception e) {
            log.error("更新定点请求失败", e);
            return Result.failure(new RuntimeException("更新定点请求失败", e));
        }
    }

    @Override
    public Result<QueryVO<ApplicationVO>> queryApplication(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, ApplicationListRequest.Conditions conditions) {

        log.debug("查询定点请求列表, 页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<TApplicationInfo> page = new Page<>(pageNum, pageSize);

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
        LambdaQueryWrapper<TApplicationInfo> queryWrapper = new LambdaQueryWrapper<>();
        // 模糊查询（文本类字段）
        queryWrapper.like(conditions.getPersonID() != null, TApplicationInfo::getPersonID, conditions.getPersonID())
                .like(conditions.getApprovalNumber() != null, TApplicationInfo::getApprovalNumber, conditions.getApprovalNumber())
                .like(conditions.getMedicalInstitutionCode() != null, TApplicationInfo::getMedicalInstitutionCode, conditions.getMedicalInstitutionCode())
                .like(conditions.getApprovalOpinions() != null, TApplicationInfo::getApprovalOpinions, conditions.getApprovalOpinions());
        // 精确查询（分类类字段，0/1/2等）
        queryWrapper
                .eq(conditions.getApprovalCategory() != null, TApplicationInfo::getApprovalCategory, conditions.getApprovalCategory())
                .eq(conditions.getStartDate() != null, TApplicationInfo::getStartDate, conditions.getStartDate())
                .eq(conditions.getTerminationDate() != null, TApplicationInfo::getTerminationDate, conditions.getTerminationDate())
                .eq(conditions.getApprover() != null, TApplicationInfo::getApprover, conditions.getApprover())
                .eq(conditions.getApprovalFlag() != null, TApplicationInfo::getApprovalFlag, conditions.getApprovalFlag())
                .eq(conditions.getApprovalDate() != null, TApplicationInfo::getApprovalDate, conditions.getApprovalDate());


        // 日期范围查询
        queryWrapper.between(conditions.getStartDate() != null, TApplicationInfo::getStartDate, conditions.getStartDate(), conditions.getTerminationDate());
        // 执行分页查询

        IPage<TApplicationInfo> applicationPage = applicationMapper.selectPage(page, queryWrapper);

        // 转换为VO
        QueryVO<ApplicationVO> queryVO = new QueryVO<>();
        queryVO.setData(applicationPage.getRecords().stream().map(application -> {
            ApplicationVO applicationVO = new ApplicationVO();
            BeanUtils.copyProperties(application, applicationVO);
            return applicationVO;
        }).toList());
        queryVO.setTotal(applicationPage.getTotal());
        queryVO.setPageNum((int) applicationPage.getCurrent());
        queryVO.setPageSize((int) applicationPage.getSize());

        log.info("定点申请查询成功, 共{}条记录", applicationPage.getTotal());
        return Result.success(queryVO);
    }
}
