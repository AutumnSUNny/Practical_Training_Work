package com.sun.praticaltrainingwork.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("people")
public class People implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String peopleId;

    private String idType;

    private String idNum;

    private String name;

    private String sex;

    private String nationality;

    private Date brithday;

    private Date workDate;

    private Date retirementDate;

    private String retirement;

    private String residenceType;

    private String residenceAdress;

    private String education;

    private String politicalStatus;

    private String identity;

    private String employment;

    private String technicalPosition;

    private String workerLevel;

    private String marriage;

    private String administrativePosition;

    private String note;

    private String companyId;

    private String medicalPersonnel;

    private String health;

    private String modelWorker;

    private String cadre;

    private String civilServant;

    private String authorizedStrength;

    private String residentType;

    private String flexibleEmployment;

    private String migrantWorker;

    private String employer;

    private String militaryPersonnel;

    private String socialSecurityId;

    private String medinsId;
}