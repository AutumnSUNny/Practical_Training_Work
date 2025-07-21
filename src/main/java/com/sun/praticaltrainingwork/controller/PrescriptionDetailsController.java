package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TPrescriptionDetails;
import com.sun.praticaltrainingwork.domain.DTO.Prescription.PrescriptionDetailsAddReq;
import com.sun.praticaltrainingwork.domain.DTO.Prescription.PrescriptionDetailsQueryReq;
import com.sun.praticaltrainingwork.domain.VO.Restful;
import com.sun.praticaltrainingwork.service.PrescriptionDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "处方明细管理")
@RestController
@RequestMapping("/prescriptionDetails")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrescriptionDetailsController {

    private final PrescriptionDetailsService prescriptionDetailsService;

    @PostMapping("/add")
    @Operation(summary = "新增处方明细", description = "新增处方明细信息")
    public Restful.ResultJson addPrescriptionDetails(@RequestBody PrescriptionDetailsAddReq req) {
        TPrescriptionDetails prescriptionDetails = new TPrescriptionDetails();
        BeanUtils.copyProperties(req, prescriptionDetails);
        return prescriptionDetailsService.add(prescriptionDetails).toJson();
    }

    @PostMapping("/update")
    @Operation(summary = "修改处方明细", description = "修改处方明细信息")
    public Restful.ResultJson updatePrescriptionDetails(@RequestBody PrescriptionDetailsAddReq req) {
        TPrescriptionDetails prescriptionDetails = new TPrescriptionDetails();
        BeanUtils.copyProperties(req, prescriptionDetails);
        return prescriptionDetailsService.update(prescriptionDetails).toJson();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除处方明细", description = "根据ID删除处方明细")
    public Restful.ResultJson deletePrescriptionDetails(@RequestBody PrescriptionDetailsQueryReq req) {
        TPrescriptionDetails prescriptionDetails = new TPrescriptionDetails();
        BeanUtils.copyProperties(req, prescriptionDetails);
        return prescriptionDetailsService.delete(prescriptionDetails.getId()).toJson();
    }

    @PostMapping("/query")
    @Operation(summary = "查询处方明细", description = "分页查询处方明细列表")
    public Restful.ResultJson queryPrescriptionDetails(@RequestBody PrescriptionDetailsQueryReq req) {
        return prescriptionDetailsService.query(
                req.getPageNum(),
                req.getPageSize(),
                req.getSorts(),
                req.getConditions()
        ).toJson();
    }
}