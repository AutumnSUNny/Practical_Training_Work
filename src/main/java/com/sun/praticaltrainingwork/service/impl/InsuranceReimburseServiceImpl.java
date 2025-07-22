package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.praticaltrainingwork.domain.DO.*;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse.InsuranceReimburseVO;
import com.sun.praticaltrainingwork.mapper.*;
import com.sun.praticaltrainingwork.service.InsuranceReimburseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceReimburseServiceImpl extends ServiceImpl<SettlementRecordsMapper, SettlementRecords>
        implements InsuranceReimburseService {

    private final PrescriptionDetailsMapper prescriptionMapper;
    private final MinimumPaymentStandardMapper minStandardMapper;
    private final IndividualSegementSelfFundedRatioMapper ratioMapper;
    private final SettlementNegativeRecordsMapper negativeMaper;

    // 1. 计算报销并保存
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<InsuranceReimburseVO> calculateAndSaveReimburse(SettlementRecords record) {
        try {
            // 计算总金额
            BigDecimal totalAmount = calculateTotalAmount(record.getHospitalizationNumber());

            // 获取起付标准和报销比例
            TMinimumPaymentStandard minStandard = getMinimumStandard(
                    record.getMedicalCategory(),
                    record.getMedicalPersonnelCategory(),
                    record.getHospitalGrade()
            );

            TIndividualSegementSelfFundedRatio ratio = getReimbursementRatio(
                    record.getMedicalCategory(),
                    record.getMedicalPersonnelCategory(),
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
        // 查询原记录
        SettlementRecords original = baseMapper.selectById(record.getId());
        if (original == null) {
            return Result.failure(new Exception("记录不存在"));
        }

        // 校验状态
        if (original.getStatus() != 1) {
            return Result.failure(new Exception("当前状态不允许取消"));
        }

        // 校验时间
        if (LocalDateTime.now().isAfter(original.getCancelDeadline())) {
            return Result.failure(new Exception("已超过取消截止时间"));
        }

        // 创建负记录
        SettlementNegativeRecords negativeRecord = new SettlementNegativeRecords();
        negativeRecord.setSettlementId(record.getId());
        negativeRecord.setNegativeAmount(original.getReimbursementAmount().negate());
        negativeRecord.setCreateTime(LocalDateTime.now());
        negativeRecord.setRemark("报销取消");
        negativeMapper.insert(negativeRecord);

        // 更新原记录状态
        original.setStatus(2); // 已取消
        baseMapper.updateById(original);


        return Result.success("取消报销成功");
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
}