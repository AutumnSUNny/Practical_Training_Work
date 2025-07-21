package com.sun.praticaltrainingwork.domain.DTO;

import lombok.Data;

@Data
public class DiseaseAddRequest {
    private String diseaseId;
    private String diseaseName;
    private String diseaseType;
    private String diseaseReimbursementStandards;
    private String notes;
}
