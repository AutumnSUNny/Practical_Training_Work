package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TServiceFacilities;
import com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities.ServiceFacilitiesAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities.ServiceFacilitiesIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities.ServiceFacilitiesListRequest;
import com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities.ServiceFacilitiesUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.ServiceFacilitiesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "医疗服务设施管理")
@RestController
@RequestMapping("/service-facilities")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ServiceFacilitiesController {

    private final ServiceFacilitiesService serviceFacilitiesService;

    @Operation(summary = "添加服务设施")
    @PostMapping("/add")
    public ResultJson add(@RequestBody ServiceFacilitiesAddRequest request) {
        TServiceFacilities entity = new TServiceFacilities();
        BeanUtils.copyProperties(request, entity);
        return serviceFacilitiesService.add(entity).toJson();
    }

    @Operation(summary = "删除服务设施")
    @PostMapping("/delete")
    public ResultJson delete(@RequestBody ServiceFacilitiesIdRequest request) {
        return serviceFacilitiesService.delete(request.getId()).toJson();
    }

    @Operation(summary = "修改服务设施")
    @PostMapping("/update")
    public ResultJson update(@RequestBody ServiceFacilitiesUpdRequest request) {
        TServiceFacilities entity = new TServiceFacilities();
        BeanUtils.copyProperties(request, entity);
        return serviceFacilitiesService.update(entity).toJson();
    }

    @Operation(summary = "查询服务设施")
    @PostMapping("/query")
    public ResultJson query(@RequestBody ServiceFacilitiesListRequest request) {
        return serviceFacilitiesService.query(
                request.getPageNum(), request.getPageSize(), request.getSorts(), request.getConditions()).toJson();
    }
}
