package com.sun.praticaltrainingwork.domain.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class SpecialApprovalUpdRequest {
    private Integer id;

    private String approvalNumber;

    private String personID;

    private String approvalCategory;

    private Date startDate;

    private Date terminationDate;

    private String drugCode;

    private String approvalOpinions;

    private String approver;

    private Date approvalDate;

    private String approvalFlag;
}
