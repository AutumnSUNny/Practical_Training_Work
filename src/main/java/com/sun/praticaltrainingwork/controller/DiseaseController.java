package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TDisease;
import com.sun.praticaltrainingwork.domain.DTO.*;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.DiseaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "疾病管理")
@RestController
@RequestMapping("/disease")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DiseaseController {

    private final DiseaseService diseaseService;

    @Operation(summary = "添加疾病")
    @PostMapping("/add")
    public ResultJson addDisease(@RequestBody DiseaseAddRequest request) {
        TDisease disease = new TDisease();
        BeanUtils.copyProperties(request, disease);
        return diseaseService.addDisease(disease).toJson();
    }

    @Operation(summary = "删除疾病")
    @PostMapping("/delete")
    public ResultJson deleteDisease(@RequestBody DiseaseIdRequest request) {
        return diseaseService.deleteDisease(request.getId()).toJson();
    }

    @Operation(summary = "更新疾病")
    @PostMapping("/update")
    public ResultJson updateDisease(@RequestBody DiseaseUpdRequest request) {
        TDisease disease = new TDisease();
        BeanUtils.copyProperties(request, disease);
        return diseaseService.updateDisease(disease).toJson();
    }

    @Operation(summary = "查询疾病")
    @PostMapping("/query")
    public ResultJson queryDisease(@RequestBody DiseaseListRequest request) {
        return diseaseService.queryDisease(request.getPageNum(), request.getPageSize(), request.getSorts(), request.getConditions()).toJson();
    }
}
