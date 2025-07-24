// InsuranceReimburseQueryRequest.java
package com.sun.praticaltrainingwork.domain.DTO.InsuranceReimburse;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class InsuranceReimburseQueryRequest {
    // 分页参数（无需手动设置，由Controller接收）
    private Integer pageNum;
    private Integer pageSize;

    // 排序条件（字段名+是否升序）
    private Map<String, Boolean> sorts;

    // 筛选条件内部类
    @Data
    public static class Conditions {
        private String peopleId;
        private String hospitalizationNumber; // 住院号（精确匹配）
        private String medicalCategory;       // 医疗类别
        private String medicalPersonnel; // 人员类别
        private String hospitalGrade;         // 医院等级
        private Integer status;               // 状态（1-有效，2-已取消，3-已支付）
        private LocalDateTime createTimeStart; // 创建时间范围-开始
        private LocalDateTime createTimeEnd;   // 创建时间范围-结束
    }

    // 筛选条件
    private Conditions conditions;
}