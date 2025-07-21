package com.sun.praticaltrainingwork.domain.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MedicalInstitutionUpdRequest {

    private Integer id;

    private String instId;

    private String instName;

    private String instExpType;

    private String instExpLevel;

    private BigDecimal instMaxPrize;

    private Date instStarttime;

    private Date instEndtime;

    private String instValid;

    private String instHosLevel;

    private String instApprovalmark;
}
