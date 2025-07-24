package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.praticaltrainingwork.domain.DO.*;
import com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse.InsuranceReimburseQueryRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse.InsuranceReimburseVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.mapper.*;
import com.sun.praticaltrainingwork.service.InsuranceReimburseService;
import com.sun.praticaltrainingwork.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceReimburseServiceImpl extends ServiceImpl<SettlementRecordsMapper, SettlementRecords>
        implements InsuranceReimburseService {

    private final PrescriptionDetailsMapper prescriptionMapper;
    private final MinimumPaymentStandardMapper minStandardMapper;
    private final IndividualSegementSelfFundedRatioMapper ratioMapper;
    private final SettlementNegativeRecordsMapper negativeMapper;

    // 1. 计算报销并保存
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<InsuranceReimburseVO> calculateAndSaveReimburse(SettlementRecords record) {
        try {
            // 计算总金额
            BigDecimal totalAmount = calculateTotalAmount(record.getHospitalizationNumber());
            if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
                return Result.failure(new Exception("总金额计算错误"));
            }

            // 获取起付标准和报销比例
            TMinimumPaymentStandard minStandard = getMinimumStandard(
                    record.getMedicalCategory(),
                    record.getMedicalPersonnel(),
                    record.getHospitalGrade()
            );

            TIndividualSegementSelfFundedRatio ratio = getReimbursementRatio(
                    record.getMedicalCategory(),
                    record.getMedicalPersonnel(),
                    record.getHospitalGrade()
            );

            // 计算报销金额
            BigDecimal reimbursementAmount = calculateReimbursement(
                    totalAmount,
                    minStandard.getMinimumPaymentStandard(),
                    ratio.getReimbursementProportion()
            );

            // 设置计算结果
            record.setTotalAmount(totalAmount);
            record.setMinimumPaymentStandard(minStandard.getMinimumPaymentStandard());
            record.setReimbursementAmount(reimbursementAmount);
            record.setPersonalPay(totalAmount.subtract(reimbursementAmount));
            record.setAmountAfterReimbursement(totalAmount.subtract(reimbursementAmount));
            record.setStatus(1); // 有效
            record.setCreateTime(LocalDateTime.now());
            record.setCancelDeadline(LocalDateTime.now().plusDays(7));

            // 保存记录
            baseMapper.insert(record);

            // 转换为VO
            InsuranceReimburseVO vo = new InsuranceReimburseVO();
            BeanUtils.copyProperties(record, vo);
            return Result.success(vo);

        } catch (Exception e) {
            log.error("计算并保存报销失败", e);
            return Result.failure(e);
        }
    }

    // 2. 查询报销记录
    @Override
    public Result<InsuranceReimburseVO> queryReimburseByHospitalNo(String hospitalizationNumber) {
        LambdaQueryWrapper<SettlementRecords> query = new LambdaQueryWrapper<>();
        query.eq(SettlementRecords::getHospitalizationNumber, hospitalizationNumber);
        SettlementRecords record = baseMapper.selectOne(query);

        if (record == null) {
            return Result.failure(new Exception("记录不存在"));
        }

        InsuranceReimburseVO vo = new InsuranceReimburseVO();
        BeanUtils.copyProperties(record, vo);
        return Result.success(vo);
    }

    // 3. 取消报销
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> cancelReimburse(SettlementRecords record) {
        // 通过住院号查询原记录（住院号是唯一标识）
        LambdaQueryWrapper<SettlementRecords> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SettlementRecords::getHospitalizationNumber, record.getHospitalizationNumber());
        SettlementRecords original = baseMapper.selectOne(queryWrapper);

        if (original == null) {
            return Result.failure(new Exception("记录不存在（住院号：" + record.getHospitalizationNumber() + "）"));
        }

        // 校验状态（仅有效状态可取消）
        if (original.getStatus() != 1) {
            return Result.failure(new Exception("当前状态不允许取消（状态：" + original.getStatus() + "）"));
        }

        // 校验时间（未超过取消截止时间）
        if (LocalDateTime.now().isAfter(original.getCancelDeadline())) {
            return Result.failure(new Exception("已超过取消截止时间（截止时间：" + original.getCancelDeadline() + "）"));
        }

        // 创建负记录（补充住院号）
        SettlementNegativeRecords negativeRecord = new SettlementNegativeRecords();
        negativeRecord.setNegativeAmount(original.getReimbursementAmount().negate());
        negativeRecord.setCreateTime(LocalDateTime.now());
        negativeRecord.setRemark("报销取消");
        negativeRecord.setHospitalizationNumber(record.getHospitalizationNumber());
        negativeMapper.insert(negativeRecord);

        // 更新原记录状态
        original.setStatus(2); // 2-已取消
        baseMapper.updateById(original);

        log.info("住院号{}的报销记录已取消（人员ID：{}，记录ID：{}）",
                original.getHospitalizationNumber(), original.getPeopleId(), original.getId());

        return Result.success("取消报销成功（住院号：" + original.getHospitalizationNumber() + "）");
    }
    // 4. 确认支付
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> confirmPayment(String hospitalizationNumber) {
        LambdaQueryWrapper<SettlementRecords> query = new LambdaQueryWrapper<>();
        query.eq(SettlementRecords::getHospitalizationNumber, hospitalizationNumber);
        SettlementRecords record = baseMapper.selectOne(query);

        if (record == null) {
            return Result.failure(new Exception("记录不存在"));
        }

        if (record.getStatus() != 1) {
            return Result.failure(new Exception("当前状态不允许支付"));
        }

        record.setStatus(3); // 已支付
        baseMapper.updateById(record);

        return Result.success("支付成功");
    }

    // 计算总金额（从处方明细）
    private BigDecimal calculateTotalAmount(String hospitalizationNumber) {
        LambdaQueryWrapper<TPrescriptionDetails> query = new LambdaQueryWrapper<>();
        query.eq(TPrescriptionDetails::getHospitalizationNumber, hospitalizationNumber);
        return prescriptionMapper.selectList(query)
                .stream()
                .map(TPrescriptionDetails::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 获取起付标准
    private TMinimumPaymentStandard getMinimumStandard(String medicalCategory, String personnelCategory, String hospitalGrade) {
        LambdaQueryWrapper<TMinimumPaymentStandard> query = new LambdaQueryWrapper<>();
        query.eq(TMinimumPaymentStandard::getMedicalCategory, medicalCategory)
                .eq(TMinimumPaymentStandard::getMedicalPersonnelCategory, personnelCategory)
                .eq(TMinimumPaymentStandard::getHospitalLevel, hospitalGrade);
        return minStandardMapper.selectOne(query);
    }

    // 获取报销比例
    private TIndividualSegementSelfFundedRatio getReimbursementRatio(String medicalCategory, String personnelCategory, String hospitalGrade) {
        LambdaQueryWrapper<TIndividualSegementSelfFundedRatio> query = new LambdaQueryWrapper<>();
        query.eq(TIndividualSegementSelfFundedRatio::getMedicalCategory, medicalCategory)
                .eq(TIndividualSegementSelfFundedRatio::getMedicalPersonnelCategory, personnelCategory)
                .eq(TIndividualSegementSelfFundedRatio::getHospitalLevel, hospitalGrade);
        return ratioMapper.selectOne(query);
    }

    // 计算报销金额
    private BigDecimal calculateReimbursement(BigDecimal totalAmount, BigDecimal minAmount, BigDecimal ratio) {
        if (totalAmount.compareTo(minAmount) <= 0) {
            return BigDecimal.ZERO;
        }
        return totalAmount.subtract(minAmount).multiply(ratio);
    }

    @Override
    public Result<QueryVO<InsuranceReimburseVO>> queryReimburseByPage(InsuranceReimburseQueryRequest request) {
        // 1. 解析分页参数（默认第1页，每页10条）
        Integer pageNum = request.getPageNum() == null ? 1 : request.getPageNum();
        Integer pageSize = request.getPageSize() == null ? 10 : request.getPageSize();
        log.debug("开始查询报销记录，页码: {}, 每页大小: {}", pageNum, pageSize);

        // 2. 初始化分页对象
        Page<SettlementRecords> page = new Page<>(pageNum, pageSize);

        // 3. 处理排序条件（支持多字段排序）
        Map<String, Boolean> sorts = request.getSorts();
        if (sorts != null && !sorts.isEmpty()) {
            for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
                // 驼峰转下划线（如createTime -> create_time）
                String sortField = CommonUtils.camelToUnderline(entry.getKey());
                boolean isAsc = entry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortField);
                orderItem.setAsc(isAsc);
                page.addOrder(orderItem);
            }
        } else {
            // 默认排序：按创建时间降序（最新的在前）
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn("id");
            orderItem.setAsc(false);
            page.addOrder(orderItem);
        }

        // 4. 构建查询条件（conditions为null时查询所有）
        LambdaQueryWrapper<SettlementRecords> queryWrapper = new LambdaQueryWrapper<>();
        InsuranceReimburseQueryRequest.Conditions conditions = request.getConditions();

        if (conditions != null) {
            // 住院号（精确匹配，因为唯一）
            if (conditions.getHospitalizationNumber() != null && !conditions.getHospitalizationNumber().isEmpty()) {
                queryWrapper.eq(SettlementRecords::getHospitalizationNumber, conditions.getHospitalizationNumber());
            }
            // 医疗类别（精确匹配）
            if (conditions.getMedicalCategory() != null && !conditions.getMedicalCategory().isEmpty()) {
                queryWrapper.eq(SettlementRecords::getMedicalCategory, conditions.getMedicalCategory());
            }
            // 人员类别（精确匹配）
            if (conditions.getMedicalPersonnel() != null && !conditions.getMedicalPersonnel().isEmpty()) {
                queryWrapper.eq(SettlementRecords::getMedicalPersonnel, conditions.getMedicalPersonnel());
            }
            // 医院等级（精确匹配）
            if (conditions.getHospitalGrade() != null && !conditions.getHospitalGrade().isEmpty()) {
                queryWrapper.eq(SettlementRecords::getHospitalGrade, conditions.getHospitalGrade());
            }
            // 状态（精确匹配）
            if (conditions.getStatus() != null) {
                queryWrapper.eq(SettlementRecords::getStatus, conditions.getStatus());
            }
            // 创建时间范围（between）
            if (conditions.getCreateTimeStart() != null && conditions.getCreateTimeEnd() != null) {
                queryWrapper.between(
                        SettlementRecords::getCreateTime,
                        conditions.getCreateTimeStart(),
                        conditions.getCreateTimeEnd()
                );
            }
        }

        // 5. 执行分页查询
        IPage<SettlementRecords> recordsPage = baseMapper.selectPage(page, queryWrapper);

        // 6. 转换DO为VO并封装结果
        List<InsuranceReimburseVO> voList = recordsPage.getRecords().stream()
                .map(record -> {
                    InsuranceReimburseVO vo = new InsuranceReimburseVO();
                    BeanUtils.copyProperties(record, vo);
                    // 确保id字段被复制（虽然BeanUtils会自动复制同名属性，但显式设置更安全）
                    vo.setId(record.getId());
                    return vo;
                })
                .collect(Collectors.toList());

        QueryVO<InsuranceReimburseVO> queryVO = new QueryVO<>();
        queryVO.setData(voList);
        queryVO.setTotal(recordsPage.getTotal());
        queryVO.setPageNum((int) recordsPage.getCurrent());
        queryVO.setPageSize((int) recordsPage.getSize());

        log.info("报销记录分页查询成功，共查询到 {} 条记录", recordsPage.getTotal());
        return Result.success(queryVO);
    }
}