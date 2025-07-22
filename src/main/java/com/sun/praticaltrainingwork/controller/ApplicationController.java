package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TApplicationInfo;
import com.sun.praticaltrainingwork.domain.DTO.ApplicationAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.ApplicationIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.ApplicationListRequest;
import com.sun.praticaltrainingwork.domain.DTO.ApplicationUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "定点请求管理")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;


    //增加定点请求
    @Operation(description = "增加定点请求")
    @PostMapping("/add")
    public ResultJson addApplication(@RequestBody ApplicationAddRequest request) {
        TApplicationInfo application = new TApplicationInfo();
        BeanUtils.copyProperties(request, application);
        return applicationService.addApplication(application).toJson();
    }

    //删除定点请求
    @Operation(description = "删除定点请求")
    @PostMapping("/delete")
    public ResultJson deleteApplication(@RequestBody ApplicationIdRequest request) {
        return applicationService.deleteApplication(request.getId()).toJson();
    }
    //修改定点请求
    @Operation(description = "修改定点请求")
    @PostMapping("/update")
    public ResultJson updateApplication(@RequestBody ApplicationUpdRequest request) {
        TApplicationInfo application = new TApplicationInfo();
        BeanUtils.copyProperties(request, application);
        return applicationService.updateApplication(application).toJson();
    }
    //查询定点请求
    @Operation(description = "查询定点请求")
    @PostMapping("/query")
    public ResultJson queryApplication(@RequestBody ApplicationListRequest request) {
        return applicationService.queryApplication(request.getPageNum(),request.getPageSize(),request.getSorts(),request.getConditions()).toJson();
    }
}
