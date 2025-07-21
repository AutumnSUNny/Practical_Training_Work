package com.sun.praticaltrainingwork.controller;


import com.sun.praticaltrainingwork.domain.DO.TMinimumPaymentStandard;


import com.sun.praticaltrainingwork.domain.DTO.MinimumPaymentStandard.MinimumPaymentStandardAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.MinimumPaymentStandard.MinimumPaymentStandardIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.MinimumPaymentStandard.MinimumPaymentStandardListRequest;
import com.sun.praticaltrainingwork.domain.DTO.MinimumPaymentStandard.MinimumPaymentStandardUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;

import com.sun.praticaltrainingwork.service.MinimumPaymentStandardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "起付标准数据管理")
@RestController
@RequestMapping("/minimumPaymentStandard")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MinimumPaymentStandardController {

    private final MinimumPaymentStandardService minimumPaymentStandardService;

    @Operation(summary = "添加起付标准数据", description = "添加起付标准相关数据")
    @PostMapping("/add")
    public ResultJson addTMinimumPaymentStandard(@RequestBody MinimumPaymentStandardAddRequest request) {
        TMinimumPaymentStandard entity = new TMinimumPaymentStandard();
        BeanUtils.copyProperties(request, entity);
        return minimumPaymentStandardService.addTMinimumPaymentStandard(entity).toJson();
    }

    @Operation(summary = "删除起付标准数据", description = "根据ID删除起付标准数据")
    @PostMapping("/delete")
    public ResultJson deleteTMinimumPaymentStandard(@RequestBody MinimumPaymentStandardIdRequest request) {
        return minimumPaymentStandardService.deleteTMinimumPaymentStandard(request.getId()).toJson();
    }

    @Operation(summary = "修改起付标准数据", description = "修改起付标准相关数据")
    @PostMapping("/update")
    public ResultJson updateTMinimumPaymentStandard(@RequestBody MinimumPaymentStandardUpdRequest request) {
        TMinimumPaymentStandard entity = new TMinimumPaymentStandard();
        BeanUtils.copyProperties(request, entity);
        return minimumPaymentStandardService.updateTMinimumPaymentStandard(entity).toJson();
    }

    @Operation(summary = "查询起付标准数据", description = "分页查询起付标准数据列表")
    @PostMapping("/query")
    public ResultJson queryTMinimumPaymentStandard(@RequestBody MinimumPaymentStandardListRequest request) {
        return minimumPaymentStandardService.queryTMinimumPaymentStandard(
                request.getPageNum(),
                request.getPageSize(),
                request.getSorts(),
                request.getConditions()
        ).toJson();
    }
}