package com.sun.praticaltrainingwork.domain.VO;

import lombok.Data;

@Data
public class TDiseaseVO {
    private Integer id;
    private String diseaseId;
    private String diseaseName;
    private String diseaseType;
    private String diseaseReimbursementStandards;
    private String notes;
}
