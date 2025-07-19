package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_service_facilities")
public class TServiceFacilities implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String serId;

    private String serName;

    private String serExpType;

    private Date serStarttime;

    private Date serEndtime;

    private String serValid;
}
