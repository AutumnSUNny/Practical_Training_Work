package com.sun.praticaltrainingwork.domain.VO.Company;

import lombok.Data;

@Data
public class CompanyVO {
    private Integer id;
    private String companyId;
    private String companyName;
    private String companyType;
    private String address;
    private String postcode;
    private String phoneNumber;
}
