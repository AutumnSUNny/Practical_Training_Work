package com.sun.praticaltrainingwork.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.People;

import com.sun.praticaltrainingwork.domain.DTO.People.PeopleListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.People.PeopleVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;

import com.sun.praticaltrainingwork.mapper.PeopleMapper;
import com.sun.praticaltrainingwork.service.PeopleService;
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
public class PeopleServiceImpl implements PeopleService {

    private final PeopleMapper peopleMapper;

    @Override
    public Result<Void> addPeople(People people) {
        log.info("添加人员信息: {}", people.getName());
        peopleMapper.insert(people);
        return Result.success(null);
    }

    @Override
    public Result<Void> deletePeople(Integer id) {
        log.info("删除人员信息, ID: {}", id);
        peopleMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> updatePeople(People people) {
        log.info("修改人员信息, ID: {}", people.getId());
        peopleMapper.updateById(people);
        return Result.success(null);
    }

    @Override
    public Result<QueryVO<PeopleVO>> queryPeople(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            PeopleListRequest.Conditions conditions
    ) {
        log.debug("查询人员列表, 页码: {}, 每页大小: {}", pageNum, pageSize);
        Page<People> page = new Page<>(pageNum, pageSize);

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
        LambdaQueryWrapper<People> queryWrapper = new LambdaQueryWrapper<>();
        // 模糊查询（文本类字段）
        queryWrapper.like(conditions.getPeopleId() != null, People::getPeopleId, conditions.getPeopleId())
                .like(conditions.getName() != null, People::getName, conditions.getName())
                .like(conditions.getIdNum() != null, People::getIdNum, conditions.getIdNum())
                .like(conditions.getResidenceAdress() != null, People::getResidenceAdress, conditions.getResidenceAdress());

        // 精确查询（分类类字段，0/1/2等）
        queryWrapper.eq(conditions.getIdType() != null, People::getIdType, conditions.getIdType())
                .eq(conditions.getSex() != null, People::getSex, conditions.getSex())
                .eq(conditions.getNationality() != null, People::getNationality, conditions.getNationality())
                .eq(conditions.getRetirement() != null, People::getRetirement, conditions.getRetirement())
                .eq(conditions.getResidenceType() != null, People::getResidenceType, conditions.getResidenceType())
                .eq(conditions.getEducation() != null, People::getEducation, conditions.getEducation())
                .eq(conditions.getPoliticalStatus() != null, People::getPoliticalStatus, conditions.getPoliticalStatus())
                .eq(conditions.getIdentity() != null, People::getIdentity, conditions.getIdentity())
                .eq(conditions.getEmployment() != null, People::getEmployment, conditions.getEmployment())
                .eq(conditions.getTechnicalPosition() != null, People::getTechnicalPosition, conditions.getTechnicalPosition())
                .eq(conditions.getWorkerLevel() != null, People::getWorkerLevel, conditions.getWorkerLevel())
                .eq(conditions.getMarriage() != null, People::getMarriage, conditions.getMarriage())
                .eq(conditions.getAdministrativePosition() != null, People::getAdministrativePosition, conditions.getAdministrativePosition())
                .eq(conditions.getCompanyId() != null, People::getCompanyId, conditions.getCompanyId())
                .eq(conditions.getMedicalPersonnel() != null, People::getMedicalPersonnel, conditions.getMedicalPersonnel())
                .eq(conditions.getHealth() != null, People::getHealth, conditions.getHealth())
                .eq(conditions.getModelWorker() != null, People::getModelWorker, conditions.getModelWorker())
                .eq(conditions.getCadre() != null, People::getCadre, conditions.getCadre())
                .eq(conditions.getCivilServant() != null, People::getCivilServant, conditions.getCivilServant())
                .eq(conditions.getAuthorizedStrength() != null, People::getAuthorizedStrength, conditions.getAuthorizedStrength())
                .eq(conditions.getResidentType() != null, People::getResidentType, conditions.getResidentType())
                .eq(conditions.getFlexibleEmployment() != null, People::getFlexibleEmployment, conditions.getFlexibleEmployment())
                .eq(conditions.getMigrantWorker() != null, People::getMigrantWorker, conditions.getMigrantWorker())
                .eq(conditions.getEmployer() != null, People::getEmployer, conditions.getEmployer())
                .eq(conditions.getMilitaryPersonnel() != null, People::getMilitaryPersonnel, conditions.getMilitaryPersonnel())
                .eq(conditions.getSocialSecurityId() != null, People::getSocialSecurityId, conditions.getSocialSecurityId())
                .eq(conditions.getMedinsId() != null, People::getMedinsId, conditions.getMedinsId());

        // 日期范围查询
        queryWrapper.between(conditions.getBrithday() != null, People::getBrithday, conditions.getBrithday(), conditions.getBrithday())
                .between(conditions.getWorkDate() != null, People::getWorkDate, conditions.getWorkDate(), conditions.getWorkDate())
                .between(conditions.getRetirementDate() != null, People::getRetirementDate, conditions.getRetirementDate(), conditions.getRetirementDate());

        // 执行分页查询
        IPage<People> peoplePage = peopleMapper.selectPage(page, queryWrapper);

        // 转换为VO
        QueryVO<PeopleVO> queryVO = new QueryVO<>();
        queryVO.setData(peoplePage.getRecords().stream().map(people -> {
            PeopleVO peopleVO = new PeopleVO();
            BeanUtils.copyProperties(people, peopleVO);
            return peopleVO;
        }).toList());
        queryVO.setTotal(peoplePage.getTotal());
        queryVO.setPageNum((int) peoplePage.getCurrent());
        queryVO.setPageSize((int) peoplePage.getSize());

        log.info("人员列表查询成功, 共{}条记录", peoplePage.getTotal());
        return Result.success(queryVO);
    }
}