package com.sun.praticaltrainingwork.domain.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Map;

@Data
public class DiseaseListRequest {
    @Nullable
    private Integer pageNum = 1;
    @Nullable
    private Integer pageSize = 10;

    @Nullable
    private Map<String, Boolean> sorts;

    @Nullable
    private Conditions conditions;

    @Data
    public static class Conditions {
        @Nullable
        private String diseaseId;

        @Nullable
        private String diseaseName;

        @Nullable
        private String diseaseType;

        @Nullable
        private String diseaseReimbursementStandards;

        @Nullable
        private String notes;
    }
}
