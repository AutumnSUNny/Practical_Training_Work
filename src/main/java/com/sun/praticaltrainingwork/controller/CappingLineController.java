package com.sun.praticaltrainingwork.controller;


import com.sun.praticaltrainingwork.domain.DO.TCappingLine;

import com.sun.praticaltrainingwork.domain.DTO.CappingLine.CappingLineAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.CappingLine.CappingLineIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.CappingLine.CappingLineListRequest;
import com.sun.praticaltrainingwork.domain.DTO.CappingLine.CappingLineUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;

import com.sun.praticaltrainingwork.service.CappingLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "封顶线数据管理")
@RestController
@RequestMapping("/cappingLine")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CappingLineController {

    private final CappingLineService cappingLineService;

    @Operation(summary = "添加封顶线数据", description = "添加封顶线相关数据")
    @PostMapping("/add")
    public ResultJson addTCappingLine(@RequestBody CappingLineAddRequest request) {
        TCappingLine tCappingLine = new TCappingLine();
        BeanUtils.copyProperties(request, tCappingLine);
        return cappingLineService.addTCappingLine(tCappingLine).toJson();
    }

    @Operation(summary = "删除封顶线数据", description = "根据ID删除封顶线数据")
    @PostMapping("/delete")
    public ResultJson deleteTCappingLine(@RequestBody CappingLineIdRequest request) {
        return cappingLineService.deleteTCappingLine(request.getId()).toJson();
    }

    @Operation(summary = "修改封顶线数据", description = "修改封顶线相关数据")
    @PostMapping("/update")
    public ResultJson updateTCappingLine(@RequestBody CappingLineUpdRequest request) {
        TCappingLine tCappingLine = new TCappingLine();
        BeanUtils.copyProperties(request, tCappingLine);
        return cappingLineService.updateTCappingLine(tCappingLine).toJson();
    }

    @Operation(summary = "查询封顶线数据", description = "分页查询封顶线数据列表")
    @PostMapping("/query")
    public ResultJson queryTCappingLine(@RequestBody CappingLineListRequest request) {
        return cappingLineService.queryTCappingLine(
                request.getPageNum(),
                request.getPageSize(),
                request.getSorts(),
                request.getConditions()
        ).toJson();
    }
}
