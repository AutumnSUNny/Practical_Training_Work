package com.sun.praticaltrainingwork.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_special_approval")
public class TSpecialApproval implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String approvalNumber;

    private String personId;

    private String approvalCategory;

    private Date startDate;

    private Date terminationDate;

    private String drugCode;

    private String approvalOpinions;

    private String approver;

    private Date approvalDate;

    private String approvalFlag;
}
