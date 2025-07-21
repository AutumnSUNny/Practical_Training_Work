package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TMedicine;
import com.sun.praticaltrainingwork.domain.DTO.Medicine.MedicineAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.Medicine.MedicineQueryRequest;
import com.sun.praticaltrainingwork.domain.DTO.Medicine.MedicineUpdateRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful;
import com.sun.praticaltrainingwork.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name="药品信息管理")
@RestController
@RequestMapping("/medicine")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MedicineController {
    private final MedicineService medicineService;

    @Operation(summary = "新增药品",description = "新增药品")
    @PostMapping("/add")
    public Restful.ResultJson addMedicine(@RequestBody MedicineAddRequest request){
        TMedicine tMedicine= new TMedicine();
        BeanUtils.copyProperties(request,tMedicine);
        return medicineService.addMedicine(tMedicine).toJson();
    }

    @Operation(summary = "修改药品",description = "修改药品")
    @PostMapping("/update")
    public Restful.ResultJson updateMedicine(@RequestBody MedicineUpdateRequest request){
        TMedicine tMedicine= new TMedicine();
        BeanUtils.copyProperties(request,tMedicine);
        return medicineService.updateMedicine(tMedicine).toJson();
    }

    @Operation(summary = "删除药品",description = "删除药品")
    @PostMapping("/delete")
    public Restful.ResultJson deleteMedicine(@RequestBody MedicineUpdateRequest request){
        TMedicine tMedicine= new TMedicine();
        BeanUtils.copyProperties(request,tMedicine);
        return medicineService.deleteMedicine(tMedicine.getId()).toJson();
    }

    @Operation(summary = "查询药品",description = "查询药品")
    @PostMapping("/query")
    public Restful.ResultJson queryMedicine(@RequestBody MedicineQueryRequest request){
        return medicineService.queryMedicine(request.getPageNum(),request.getPageSize(),
                request.getSorts(),request.getConditions()).toJson();
    }
}
