package com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ServiceFacilitiesListRequest {
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
        private String serId;
        private String serName;
        private String serExpType;
        private Date serStarttime;
        private Date serEndtime;
        private String serValid;
    }
}
