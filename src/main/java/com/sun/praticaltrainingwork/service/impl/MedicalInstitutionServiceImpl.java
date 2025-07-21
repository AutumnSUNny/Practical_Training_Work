package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TMedicalInsititution;
import com.sun.praticaltrainingwork.domain.DTO.MedicalInstitution.MedicalInstitutionListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.MedicalInstitution.MedicalInstitutionVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.mapper.MedicalInsititutionMapper;
import com.sun.praticaltrainingwork.service.MedicalInstitutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalInstitutionServiceImpl implements MedicalInstitutionService {

    private final MedicalInsititutionMapper institutionInformationMapper;
    private final MedicalInsititutionMapper medicalInsititutionMapper;

    @Override
    public Result<Void> addInstitutionInformation(TMedicalInsititution tMedicalInsititution) {
        try {
            institutionInformationMapper.insert(tMedicalInsititution);
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(new RuntimeException("创建定点医疗机构信息失败"));
        }
    }

    @Override
    public Result<Void> updateInstitutionInformation(TMedicalInsititution tmedicalInsititution) {
        try {
            institutionInformationMapper.updateById(tmedicalInsititution);
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(new RuntimeException("更新定点医疗机构信息失败"));
        }
    }

    @Override
    public Result<Void> deleteInstitutionInformation(Integer id) {
        try {
            institutionInformationMapper.deleteById(id);
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(new RuntimeException("删除定点医疗机构信息失败"));
        }

    }

    @Override
    public Result<QueryVO<MedicalInstitutionVO>> queryInstitutionInformation(Integer pageNum, Integer pageSize,
                                                                             Map<String, Boolean> sorts, MedicalInstitutionListRequest.Conditions conditions) {

        log.debug("查询医疗机构信息，页码: {}, 每页: {}", pageNum, pageSize);
        Page<TMedicalInsititution> page = new Page<>(pageNum, pageSize);

        // 处理排序
        if (sorts != null) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = entry.getKey();
                boolean isDesc = entry.getValue();

                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(!isDesc); // true 表示升序，false 表示降序

                page.addOrder(orderItem);
            }
        }

        // 构建查询条件
        LambdaQueryWrapper<TMedicalInsititution> queryWrapper = new LambdaQueryWrapper<>();
        if (conditions != null) {
            queryWrapper
                    .like(conditions.getInstId() != null, TMedicalInsititution::getInstId, conditions.getInstId())
                    .like(conditions.getInstName() != null, TMedicalInsititution::getInstName, conditions.getInstName())
                    .eq(conditions.getInstExpType() != null, TMedicalInsititution::getInstExpType, conditions.getInstExpType())
                    .eq(conditions.getInstExpLevel() != null, TMedicalInsititution::getInstExpLevel, conditions.getInstExpLevel())
                    .eq(conditions.getInstMaxPrize() != null, TMedicalInsititution::getInstMaxPrize, conditions.getInstMaxPrize())
                    .eq(conditions.getInstStarttime() != null, TMedicalInsititution::getInstStarttime, conditions.getInstStarttime())
                    .eq(conditions.getInstEndtime() != null, TMedicalInsititution::getInstEndtime, conditions.getInstEndtime())
                    .eq(conditions.getInstValid() != null, TMedicalInsititution::getInstValid, conditions.getInstValid())
                    .eq(conditions.getInstHosLevel() != null, TMedicalInsititution::getInstHosLevel, conditions.getInstHosLevel())
                    .eq(conditions.getInstApprovalmark() != null, TMedicalInsititution::getInstApprovalmark, conditions.getInstApprovalmark());
        }

        // 执行分页查询
        IPage<TMedicalInsititution> resultPage = medicalInsititutionMapper.selectPage(page, queryWrapper);

        // 构建返回结果
        QueryVO<MedicalInstitutionVO> queryVO = new QueryVO<>();
        queryVO.setData(resultPage.getRecords().stream()
                .map(institution -> {
                    MedicalInstitutionVO institutionVO = new MedicalInstitutionVO();
                    BeanUtils.copyProperties(institution, institutionVO);
                    return institutionVO;
                })
                .toList());
        queryVO.setTotal(resultPage.getTotal());
        queryVO.setPageNum((int) resultPage.getCurrent());
        queryVO.setPageSize((int) resultPage.getSize());

        return Result.success(queryVO);
    }
}
