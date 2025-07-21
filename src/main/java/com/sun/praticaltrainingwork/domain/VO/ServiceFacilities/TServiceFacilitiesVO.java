package com.sun.praticaltrainingwork.domain.VO.ServiceFacilities;

import lombok.Data;

import java.util.Date;

@Data
public class TServiceFacilitiesVO {
    private Integer id;
    private String serId;
    private String serName;
    private String serExpType;
    private Date serStarttime;
    private Date serEndtime;
    private String serValid;
}
