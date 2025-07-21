package com.sun.praticaltrainingwork.domain.DTO.Company;

import lombok.Data;

@Data
public class CompanyAddRequest {
    private String companyId;
    private String companyName;
    private String companyType;
    private String address;
    private String postcode;
    private String phoneNumber;
}