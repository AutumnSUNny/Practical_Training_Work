package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.praticaltrainingwork.domain.DO.TIndividualSegementSelfFundedRatio;
import com.sun.praticaltrainingwork.domain.DO.TMinimumPaymentStandard;
import com.sun.praticaltrainingwork.domain.DO.TPrescriptionDetails;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.InsuranceReimburse.InsuranceReimburseVO;
import com.sun.praticaltrainingwork.mapper.IndividualSegementSelfFundedRatioMapper;
import com.sun.praticaltrainingwork.mapper.MinimumPaymentStandardMapper;
import com.sun.praticaltrainingwork.mapper.PrescriptionDetailsMapper;
import com.sun.praticaltrainingwork.service.InsuranceReimburseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InsuranceReimburseServiceImpl implements InsuranceReimburseService {

    private final PrescriptionDetailsMapper prescriptionDetailsMapper;
    private final MinimumPaymentStandardMapper minimumPaymentStandardMapper;
    private final IndividualSegementSelfFundedRatioMapper individualSegementSelfFundedRatioMapper;

    @Override
    public Result<InsuranceReimburseVO> insuranceReimburse(Integer peopleId, String hospitalGrade, String hospitalizationNumber, String medicalCategory, String medicalPersonnel) {
        try {
            // 1. 根据住院号计算总金额
            LambdaQueryWrapper<TPrescriptionDetails> prescriptionQueryWrapper = new LambdaQueryWrapper<>();
            prescriptionQueryWrapper.eq(TPrescriptionDetails::getHospitalizationNumber, hospitalizationNumber);
            BigDecimal totalAmount = prescriptionDetailsMapper.selectList(prescriptionQueryWrapper)
                    .stream()
                    .map(TPrescriptionDetails::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // 2. 根据医疗类别、医疗人员类别和医院等级获取起付标准
            LambdaQueryWrapper<TMinimumPaymentStandard> minimumQueryWrapper = new LambdaQueryWrapper<>();
            minimumQueryWrapper.eq(TMinimumPaymentStandard::getMedicalCategory, medicalCategory)
                    .eq(TMinimumPaymentStandard::getMedicalPersonnelCategory, medicalPersonnel)
                    .eq(TMinimumPaymentStandard::getHospitalLevel, hospitalGrade);
            TMinimumPaymentStandard minimumPaymentStandard = minimumPaymentStandardMapper.selectOne(minimumQueryWrapper);

            if (minimumPaymentStandard == null) {
                log.error("未找到对应的起付标准信息，医疗类别: {}, 医疗人员类别: {}, 医院等级: {}", medicalCategory, medicalPersonnel, hospitalGrade);
                return Result.failure(new Exception("未找到对应的起付标准信息"));
            }

            BigDecimal minimumAmount = minimumPaymentStandard.getMinimumPaymentStandard();

            // 3. 根据医疗类别、医疗人员类别和医院等级获取报销比例
            LambdaQueryWrapper<TIndividualSegementSelfFundedRatio> ratioQueryWrapper = new LambdaQueryWrapper<>();
            ratioQueryWrapper.eq(TIndividualSegementSelfFundedRatio::getMedicalCategory, medicalCategory)
                    .eq(TIndividualSegementSelfFundedRatio::getMedicalPersonnelCategory, medicalPersonnel)
                    .eq(TIndividualSegementSelfFundedRatio::getHospitalLevel, hospitalGrade);
            TIndividualSegementSelfFundedRatio ratio = individualSegementSelfFundedRatioMapper.selectOne(ratioQueryWrapper);

            if (ratio == null) {
                log.error("未找到对应的报销比例信息，医疗类别: {}, 医疗人员类别: {}, 医院等级: {}", medicalCategory, medicalPersonnel, hospitalGrade);
                return Result.failure(new Exception("未找到对应的报销比例信息"));
            }

            BigDecimal reimbursementProportion = ratio.getReimbursementProportion();

            // 4. 计算报销金额和自付金额
            BigDecimal reimbursementAmount;
            BigDecimal personalExpenses;
            BigDecimal amountAfterReimbursement; // 新增变量

            if (totalAmount.compareTo(minimumAmount) < 0) {
                // 总金额未达到起付标准，全额自付
                reimbursementAmount = BigDecimal.ZERO;
                personalExpenses = totalAmount;
                amountAfterReimbursement = totalAmount; // 报销后金额等于总金额
            } else {
                // 计算可报销金额
                reimbursementAmount = totalAmount.subtract(minimumAmount).multiply(reimbursementProportion);
                personalExpenses = totalAmount.subtract(reimbursementAmount);
                amountAfterReimbursement = totalAmount.subtract(reimbursementAmount); // 报销后金额 = 总金额 - 报销金额
            }

            log.info("总金额: {}, 起付标准: {}, 报销比例: {}, 报销金额: {}, 个人自费金额: {}, 报销后金额: {}",
                    totalAmount, minimumAmount, reimbursementProportion, reimbursementAmount, personalExpenses, amountAfterReimbursement);

            // 构建返回结果对象
            InsuranceReimburseVO result = new InsuranceReimburseVO(
                    peopleId,
                    hospitalGrade,
                    hospitalizationNumber,
                    medicalCategory,
                    medicalPersonnel,
                    totalAmount,
                    minimumAmount,
                    reimbursementAmount,
                    personalExpenses,
                    amountAfterReimbursement // 传递新增字段
            );

            return Result.success(result);
        } catch (Exception e) {
            log.error("保险报销计算出错", e);
            return Result.failure(e);
        }
    }
}