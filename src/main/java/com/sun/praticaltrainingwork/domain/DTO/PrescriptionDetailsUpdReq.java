package com.sun.praticaltrainingwork.domain.DTO;

import com.sun.praticaltrainingwork.mapper.PrescriptionDetailsMapper;
import lombok.Data;

@Data
public class PrescriptionDetailsUpdReq extends PrescriptionDetailsAddReq {
    Integer id;
}
