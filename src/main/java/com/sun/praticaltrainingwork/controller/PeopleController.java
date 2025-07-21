package com.sun.praticaltrainingwork.controller;


import com.sun.praticaltrainingwork.domain.DO.People;

import com.sun.praticaltrainingwork.domain.DTO.People.PeopleAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.People.PeopleIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.People.PeopleListRequest;
import com.sun.praticaltrainingwork.domain.DTO.People.PeopleUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "人员信息管理")
@RestController
@RequestMapping("/people")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PeopleController {

    private final PeopleService peopleService;

    @Operation(summary = "添加人员信息", description = "添加人员基本信息")
    @PostMapping("/add")
    public ResultJson addPeople(@RequestBody PeopleAddRequest request) {
        People people = new People();
        BeanUtils.copyProperties(request, people);
        return peopleService.addPeople(people).toJson();
    }

    @Operation(summary = "删除人员信息", description = "根据ID删除人员信息")
    @PostMapping("/delete")
    public ResultJson deletePeople(@RequestBody PeopleIdRequest request) {
        return peopleService.deletePeople(request.getId()).toJson();
    }

    @Operation(summary = "修改人员信息", description = "修改人员基本信息")
    @PostMapping("/update")
    public ResultJson updatePeople(@RequestBody PeopleUpdRequest request) {
        People people = new People();
        BeanUtils.copyProperties(request, people);
        return peopleService.updatePeople(people).toJson();
    }

    @Operation(summary = "查询人员信息", description = "分页查询人员信息列表")
    @PostMapping("/query")
    public ResultJson queryPeople(@RequestBody PeopleListRequest request) {
        return peopleService.queryPeople(
                request.getPageNum(),
                request.getPageSize(),
                request.getSorts(),
                request.getConditions()
        ).toJson();
    }
}