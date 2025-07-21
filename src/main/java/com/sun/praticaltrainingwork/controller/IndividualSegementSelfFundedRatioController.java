package com.sun.praticaltrainingwork.controller;


import com.sun.praticaltrainingwork.domain.DO.TIndividualSegementSelfFundedRatio;

import com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioListRequest;
import com.sun.praticaltrainingwork.domain.DTO.IndividualSegementSelfFundedRatio.IndividualSegementSelfFundedRatioUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.IndividualSegementSelfFundedRatioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "个人分段自费比例数据管理")
@RestController
@RequestMapping("/individualSegementSelfFundedRatio")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IndividualSegementSelfFundedRatioController {

    private final IndividualSegementSelfFundedRatioService individualSegementSelfFundedRatioService;

    @Operation(summary = "添加个人分段自费比例数据", description = "添加个人分段自费比例相关数据")
    @PostMapping("/add")
    public ResultJson addTIndividualSegementSelfFundedRatio(@RequestBody IndividualSegementSelfFundedRatioAddRequest request) {
        TIndividualSegementSelfFundedRatio entity = new TIndividualSegementSelfFundedRatio();
        BeanUtils.copyProperties(request, entity);
        return individualSegementSelfFundedRatioService.addTIndividualSegementSelfFundedRatio(entity).toJson();
    }

    @Operation(summary = "删除个人分段自费比例数据", description = "根据ID删除个人分段自费比例数据")
    @PostMapping("/delete")
    public ResultJson deleteTIndividualSegementSelfFundedRatio(@RequestBody IndividualSegementSelfFundedRatioIdRequest request) {
        return individualSegementSelfFundedRatioService.deleteTIndividualSegementSelfFundedRatio(request.getId()).toJson();
    }

    @Operation(summary = "修改个人分段自费比例数据", description = "修改个人分段自费比例相关数据")
    @PostMapping("/update")
    public ResultJson updateTIndividualSegementSelfFundedRatio(@RequestBody IndividualSegementSelfFundedRatioUpdRequest request) {
        TIndividualSegementSelfFundedRatio entity = new TIndividualSegementSelfFundedRatio();
        BeanUtils.copyProperties(request, entity);
        return individualSegementSelfFundedRatioService.updateTIndividualSegementSelfFundedRatio(entity).toJson();
    }

    @Operation(summary = "查询个人分段自费比例数据", description = "分页查询个人分段自费比例数据列表")
    @PostMapping("/query")
    public ResultJson queryTIndividualSegementSelfFundedRatio(@RequestBody IndividualSegementSelfFundedRatioListRequest request) {
        return individualSegementSelfFundedRatioService.queryTIndividualSegementSelfFundedRatio(
                request.getPageNum(),
                request.getPageSize(),
                request.getSorts(),
                request.getConditions()
        ).toJson();
    }
}