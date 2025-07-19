package com.sun.praticaltrainingwork.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("company")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String companyId;

    private String companyName;

    private String companyType;

    private String address;

    private String postcode;

    private String phoneNumber;
}
