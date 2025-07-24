package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TPersonnelVisitsInfo;
import com.sun.praticaltrainingwork.domain.DTO.PersonnelVisitsInfo.PersonnelVisitsInfoQueryReq;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.PersonnelVisitsInfo.TPersonnelVisitsInfoVO;
import com.sun.praticaltrainingwork.mapper.PersonnelVisitsInfoMapper;
import com.sun.praticaltrainingwork.service.PersonnelVisitsInfoService;
import com.sun.praticaltrainingwork.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PersonnelVisitsInfoServiceImpl implements PersonnelVisitsInfoService {
    private final PersonnelVisitsInfoMapper personnelVisitsInfoMapper;

    @Override
    public Result<Void> add(TPersonnelVisitsInfo tPersonnelVisitInfo) {
        log.info("新增就诊记录");
        personnelVisitsInfoMapper.insert(tPersonnelVisitInfo);
        return Result.success(null);
    }

    @Override
    public Result<Void> update(TPersonnelVisitsInfo tPersonnelVisitsInfo) {
        log.info("修改就诊记录 ");
        personnelVisitsInfoMapper.updateById(tPersonnelVisitsInfo);
        return Result.success(null);
    }

    @Override
    public Result<Void> delete(Integer id) {
        log.info("删除就诊记录");
        personnelVisitsInfoMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<TPersonnelVisitsInfoVO>> query(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, PersonnelVisitsInfoQueryReq.Conditions conditions) {
        log.debug("开始查询人员就诊信息列表，页码: {}, 每页大小: {}", pageNum, pageSize);

        // 1. 初始化分页对象
        Page<TPersonnelVisitsInfo> page = new Page<>(pageNum, pageSize);

        // 2. 处理排序条件
        if (sorts != null && !sorts.isEmpty()) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                String sortField = CommonUtils.camelToUnderline( entry.getKey());
                boolean isAsc = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(isAsc);
                page.addOrder(orderItem);
            }
        }

        // 3. 构建查询条件
        LambdaQueryWrapper<TPersonnelVisitsInfo> queryWrapper = new LambdaQueryWrapper<>();

        if (conditions != null) {
            // 人员ID精确查询
            if (conditions.getPersonId() != null) {
                queryWrapper.eq(TPersonnelVisitsInfo::getPersonId, conditions.getPersonId());
            }
            // 住院号模糊查询
            if (conditions.getHospitalizationNumber() != null) {
                queryWrapper.like(TPersonnelVisitsInfo::getHospitalizationNumber, conditions.getHospitalizationNumber());
            }
            // 就诊号模糊查询
            if (conditions.getDesignatedNumber() != null) {
                queryWrapper.like(TPersonnelVisitsInfo::getDesignatedNumber, conditions.getDesignatedNumber());
            }
            // 医疗类别精确查询
            if (conditions.getMedicalCategory() != null) {
                queryWrapper.eq(TPersonnelVisitsInfo::getMedicalCategory, conditions.getMedicalCategory());
            }
            // 入院日期范围查询
            if (conditions.getAdmissionDateStart() != null && conditions.getAdmissionDateEnd() != null) {
                queryWrapper.between(TPersonnelVisitsInfo::getAdmissionDate, conditions.getAdmissionDateStart(), conditions.getAdmissionDateEnd());
            }
            // 出院日期范围查询
            if (conditions.getDischargeDateStart() != null && conditions.getDischargeDateEnd() != null) {
                queryWrapper.between(TPersonnelVisitsInfo::getDischargeDate, conditions.getDischargeDateStart(), conditions.getDischargeDateEnd());
            }
            // 疾病编码精确查询
            if (conditions.getDiseaseCode() != null) {
                queryWrapper.eq(TPersonnelVisitsInfo::getDiseaseCode, conditions.getDiseaseCode());
            }
            // 医院等级精确查询
            if (conditions.getHospitalGrade() != null) {
                queryWrapper.eq(TPersonnelVisitsInfo::getHospitalGrade, conditions.getHospitalGrade());
            }
            // 入院病情编码精确查询
            if (conditions.getAdmissionCode() != null) {
                queryWrapper.eq(TPersonnelVisitsInfo::getAdmissionCode, conditions.getAdmissionCode());
            }
            // 诊断名称模糊查询
            if (conditions.getDiagnosedName() != null) {
                queryWrapper.like(TPersonnelVisitsInfo::getDiagnosedName, conditions.getDiagnosedName());
            }
            // 出院原因模糊查询
            if (conditions.getDischargeReason() != null) {
                queryWrapper.like(TPersonnelVisitsInfo::getDischargeReason, conditions.getDischargeReason());
            }
            // 结算标志精确查询
            if (conditions.getSettlementFlag() != null) {
                queryWrapper.eq(TPersonnelVisitsInfo::getSettlementFlag, conditions.getSettlementFlag());
            }
        }

        // 4. 执行分页查询
        IPage<TPersonnelVisitsInfo> visitsInfoPage = personnelVisitsInfoMapper.selectPage(page, queryWrapper);

        // 5. 转换DO为VO并封装结果
        List<TPersonnelVisitsInfoVO> visitsInfoVOList = visitsInfoPage.getRecords().stream()
                .map(visitsInfoDO -> {
                    TPersonnelVisitsInfoVO visitsInfoVO = new TPersonnelVisitsInfoVO();
                    BeanUtils.copyProperties(visitsInfoDO, visitsInfoVO);
                    return visitsInfoVO;
                })
                .collect(Collectors.toList());

        QueryVO<TPersonnelVisitsInfoVO> queryVO = new QueryVO<>();
        queryVO.setData(visitsInfoVOList);
        queryVO.setTotal(visitsInfoPage.getTotal());
        queryVO.setPageNum((int) visitsInfoPage.getCurrent());
        queryVO.setPageSize((int) visitsInfoPage.getSize());

        log.info("人员就诊信息列表查询成功，共查询到 {} 条记录", visitsInfoPage.getTotal());
        return Result.success(queryVO);
    }
}
