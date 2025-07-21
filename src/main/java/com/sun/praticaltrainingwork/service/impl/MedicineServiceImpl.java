package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TMedicine;
import com.sun.praticaltrainingwork.domain.DTO.Medicine.MedicineQueryRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.Medicine.MedicineVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.mapper.MedicineMapper;
import com.sun.praticaltrainingwork.service.MedicineService;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) // 修正注解语法
public class MedicineServiceImpl implements MedicineService {
    private final MedicineMapper medicineMapper;

    @Override
    public Result<Void> addMedicine(TMedicine tMedicine) {
        log.info("新增药品");
        medicineMapper.insert(tMedicine);
        return Result.success(null);
    }

    @Override
    public Result<Void> updateMedicine(TMedicine tMedicine) {
        log.info("修改药品");
        medicineMapper.updateById(tMedicine);
        return null;
    }

    @Override
    public Result<Void> deleteMedicine(Integer id) {
        log.info("删除药品");
        medicineMapper.deleteById(id);
        return null;
    }

    @Override
    public Result<QueryVO<MedicineVO>> queryMedicine(
            Integer pageNum,
            Integer pageSize,
            Map<String, Boolean> sorts,
            MedicineQueryRequest.Conditions conditions) {

        log.debug("开始查询药品列表，页码: {}, 每页大小: {}", pageNum, pageSize);

        // 1. 初始化分页对象
        Page<TMedicine> page = new Page<>(pageNum, pageSize);

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

        // 3. 构建查询条件（核心优化：conditions为null时，不添加任何查询条件，即查询所有）
        LambdaQueryWrapper<TMedicine> queryWrapper = new LambdaQueryWrapper<>();

        // 仅当conditions不为null时，才添加条件（否则查询所有）
        if (conditions != null) {
            // 药品ID模糊查询
            if (conditions.getMedId() != null) {
                queryWrapper.like(TMedicine::getMedId, conditions.getMedId());
            }
            // 药品名称模糊查询
            if (conditions.getMedName() != null) {
                queryWrapper.like(TMedicine::getMedName, conditions.getMedName());
            }
            // 药品类型精确匹配
            if (conditions.getMedExpType() != null) {
                queryWrapper.eq(TMedicine::getMedExpType, conditions.getMedExpType());
            }
            // 药品级别精确匹配
            if (conditions.getMedExpLevel() != null) {
                queryWrapper.eq(TMedicine::getMedExpLevel, conditions.getMedExpLevel());
            }
            // 最高价格匹配
            if (conditions.getMedMaxPrice() != null) {
                queryWrapper.eq(TMedicine::getMedMaxPrice, conditions.getMedMaxPrice());
            }
            // 审批标志匹配
            if (conditions.getMedApprovalmark() != null) {
                queryWrapper.eq(TMedicine::getMedApprovalmark, conditions.getMedApprovalmark());
            }
            // 医院级别匹配
            if (conditions.getMedHosLevel() != null) {
                queryWrapper.eq(TMedicine::getMedHosLevel, conditions.getMedHosLevel());
            }
            // 有效状态匹配
            if (conditions.getMedValid() != null) {
                queryWrapper.eq(TMedicine::getMedValid, conditions.getMedValid());
            }
            // 时间范围查询
            if (conditions.getMedStarttime() != null && conditions.getMedEndtime() != null) {
                queryWrapper.between(
                        TMedicine::getMedStarttime,
                        conditions.getMedStarttime(),
                        conditions.getMedEndtime()
                );
            }
        }

        // 4. 执行分页查询（conditions为null时，queryWrapper无任何条件，即查询所有
        IPage<TMedicine> medicinePage = medicineMapper.selectPage(page, queryWrapper);


        // 5. 转换DO为VO并封装结果（不变）
        List<MedicineVO> medicineVOList = medicinePage.getRecords().stream()
                .map(medicineDO -> {
                    MedicineVO medicineVO = new MedicineVO();
                    BeanUtils.copyProperties(medicineDO, medicineVO);
                    return medicineVO;
                })
                .collect(Collectors.toList());

        QueryVO<MedicineVO> queryVO = new QueryVO<>();
        queryVO.setData(medicineVOList);
        queryVO.setTotal(medicinePage.getTotal());
        queryVO.setPageNum((int) medicinePage.getCurrent());
        queryVO.setPageSize((int) medicinePage.getSize());

        log.info("药品列表查询成功，共查询到 {} 条记录", medicinePage.getTotal());
        return Result.success(queryVO);
    }
}