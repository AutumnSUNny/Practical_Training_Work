package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.Company;

import com.sun.praticaltrainingwork.domain.DTO.Company.CompanyAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.Company.CompanyIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.Company.CompanyListRequest;
import com.sun.praticaltrainingwork.domain.DTO.Company.CompanyUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "单位信息管理")
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "添加单位信息", description = "添加单位基本信息")
    @PostMapping("/add")
    public ResultJson addCompany(@RequestBody CompanyAddRequest request) {
        Company company = new Company();
        BeanUtils.copyProperties(request, company);
        return companyService.addCompany(company).toJson();
    }

    @Operation(summary = "删除单位信息", description = "根据ID删除单位信息")
    @PostMapping("/delete")
    public ResultJson deleteCompany(@RequestBody CompanyIdRequest request) {
        return companyService.deleteCompany(request.getId()).toJson();
    }

    @Operation(summary = "修改单位信息", description = "修改单位基本信息")
    @PostMapping("/update")
    public ResultJson updateCompany(@RequestBody CompanyUpdRequest request) {
        Company company = new Company();
        BeanUtils.copyProperties(request, company);
        return companyService.updateCompany(company).toJson();
    }

    @Operation(summary = "查询单位信息", description = "分页查询单位信息列表")
    @PostMapping("/query")
    public ResultJson queryCompany(@RequestBody CompanyListRequest request) {
        return companyService.queryCompany(
                request.getPageNum(),
                request.getPageSize(),
                request.getSorts(),
                request.getConditions()
        ).toJson();
    }
}