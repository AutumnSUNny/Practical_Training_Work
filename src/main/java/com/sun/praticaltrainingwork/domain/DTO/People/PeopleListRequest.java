package com.sun.praticaltrainingwork.domain.DTO.People;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import java.util.Date;
import java.util.Map;

@Data
public class PeopleListRequest {
    @Nullable
    @Schema(description = "页码")
    private Integer pageNum = 1;

    @Nullable
    @Schema(description = "每页大小")
    private Integer pageSize = 10;

    @Getter
    @Nullable
    @Schema(description = "排序字段", example = "{\"name\":true,\"brithday\":false}")
    private Map<String, Boolean> sorts;

    @Nullable
    @Schema(description = "查询条件")
    private Conditions conditions;

    @Data
    public class Conditions {
        @Nullable
        @Schema(description = "个人ID")
        private String peopleId;

        @Nullable
        @Schema(description = "证件类型")
        private String idType;

        @Nullable
        @Schema(description = "证件编号")
        private String idNum;

        @Nullable
        @Schema(description = "姓名")
        private String name;

        @Nullable
        @Schema(description = "性别")
        private String sex;

        @Nullable
        @Schema(description = "民族")
        private String nationality;

        @Nullable
        @Schema(description = "出生日期")
        private Date brithday;

        @Nullable
        @Schema(description = "参加工作日期")
        private Date workDate;

        @Nullable
        @Schema(description = "离退休日期")
        private Date retirementDate;

        @Nullable
        @Schema(description = "离退休状态")
        private String retirement;

        @Nullable
        @Schema(description = "户口性质")
        private String residenceType;

        @Nullable
        @Schema(description = "户口所在地")
        private String residenceAdress;

        @Nullable
        @Schema(description = "文化程度")
        private String education;

        @Nullable
        @Schema(description = "政治面貌")
        private String politicalStatus;

        @Nullable
        @Schema(description = "个人身份")
        private String identity;

        @Nullable
        @Schema(description = "用工形式")
        private String employment;

        @Nullable
        @Schema(description = "专业技术职务")
        private String technicalPosition;

        @Nullable
        @Schema(description = "工人技术等级")
        private String workerLevel;

        @Nullable
        @Schema(description = "婚姻状况")
        private String marriage;

        @Nullable
        @Schema(description = "行政职务")
        private String administrativePosition;

        @Nullable
        @Schema(description = "单位编码")
        private String companyId;

        @Nullable
        @Schema(description = "医疗人员类别")
        private String medicalPersonnel;

        @Nullable
        @Schema(description = "健康状况")
        private String health;

        @Nullable
        @Schema(description = "劳模标志")
        private String modelWorker;

        @Nullable
        @Schema(description = "干部标志")
        private String cadre;

        @Nullable
        @Schema(description = "公务员标志")
        private String civilServant;

        @Nullable
        @Schema(description = "在编标志")
        private String authorizedStrength;

        @Nullable
        @Schema(description = "居民类别")
        private String residentType;

        @Nullable
        @Schema(description = "灵活就业标志")
        private String flexibleEmployment;

        @Nullable
        @Schema(description = "农民工标志")
        private String migrantWorker;

        @Nullable
        @Schema(description = "雇主标志")
        private String employer;

        @Nullable
        @Schema(description = "军转人员标志")
        private String militaryPersonnel;

        @Nullable
        @Schema(description = "社保卡号")
        private String socialSecurityId;

        @Nullable
        @Schema(description = "定点医疗机构编码")
        private String medinsId;
    }
}