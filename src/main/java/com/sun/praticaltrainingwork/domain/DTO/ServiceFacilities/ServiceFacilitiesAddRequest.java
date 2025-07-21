package com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceFacilitiesAddRequest {
    private String serId;
    private String serName;
    private String serExpType;
    private Date serStarttime;
    private Date serEndtime;
    private String serValid;
}
