package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TPersonnelVisitsInfo;
import com.sun.praticaltrainingwork.domain.DTO.PersonnelVisitsInfo.PersonnelVisitsInfoAddReq;
import com.sun.praticaltrainingwork.domain.DTO.PersonnelVisitsInfo.PersonnelVisitsInfoQueryReq;
import com.sun.praticaltrainingwork.domain.VO.Restful;
import com.sun.praticaltrainingwork.service.PersonnelVisitsInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name="人员就诊信息")
@RestController
@RequestMapping("/PersonnelVisitsInfo")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PersonnelVisitsInfoController {
    private final PersonnelVisitsInfoService personnelVisitsInfoService;
    @PostMapping("/add")
    @Operation(summary = "新增就诊信息",description = "新增就诊信息")
    public Restful.ResultJson addPersonnelVisitInfo(PersonnelVisitsInfoAddReq req){
        TPersonnelVisitsInfo tPersonnelVisitsInfo = new TPersonnelVisitsInfo();
        BeanUtils.copyProperties(req,tPersonnelVisitsInfo);
        return personnelVisitsInfoService.add(tPersonnelVisitsInfo).toJson();
    }

    @PostMapping("/update")
    @Operation(summary = "修改就诊信息 ",description = "修改就诊信息")
    public Restful.ResultJson update(PersonnelVisitsInfoAddReq req){
        TPersonnelVisitsInfo tPersonnelVisitsInfo = new TPersonnelVisitsInfo();
        BeanUtils.copyProperties(req,tPersonnelVisitsInfo);
        return personnelVisitsInfoService.update(tPersonnelVisitsInfo).toJson();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除就诊信息",description = "删除就诊信息")
    public Restful.ResultJson deletePersonnelVisitInfo(@RequestBody PersonnelVisitsInfoQueryReq req){
        TPersonnelVisitsInfo tPersonnelVisitsInfo = new TPersonnelVisitsInfo();
        BeanUtils.copyProperties(req,tPersonnelVisitsInfo);
        return personnelVisitsInfoService.delete(tPersonnelVisitsInfo.getId()).toJson();
    }

    @PostMapping("/query")
    @Operation(summary = "查询就诊信息",description = "查询就诊信息")
    public Restful.ResultJson queryPersonnelVisitInfo(@RequestBody PersonnelVisitsInfoQueryReq req){
        return personnelVisitsInfoService.query(req.getPageNum(),req.getPageSize(),
                req.getSorts(),req.getConditions()).toJson();
    }
}
