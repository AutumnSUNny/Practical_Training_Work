package com.sun.praticaltrainingwork.domain.DTO.Diagnosis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DiagnosisIdRequest {
    @Schema(description = "项目ID")
    private Integer id;;
}
