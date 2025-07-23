package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TMedicalInsititution;
import com.sun.praticaltrainingwork.domain.DTO.MedicalInstitution.MedicalInstitutionAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.MedicalInstitution.MedicalInstitutionIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.MedicalInstitution.MedicalInstitutionListRequest;
import com.sun.praticaltrainingwork.domain.DTO.MedicalInstitution.MedicalInstitutionUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.MedicalInstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "定点医疗机构信息维护")
@RequestMapping("/MedicalInstitution")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MedicalInstitutionController {

    private final MedicalInstitutionService institutionInformationService;

    @Operation(summary = "新增定点医疗机构信息")
    @PostMapping("/add")
    public ResultJson addInstitutionInformation(@RequestBody MedicalInstitutionAddRequest request){
        TMedicalInsititution tmedicalInsititution = new TMedicalInsititution();
        BeanUtils.copyProperties(request,tmedicalInsititution);
        return institutionInformationService.addInstitutionInformation(tmedicalInsititution).toJson();
    }

    @Operation(summary = "修改定点医疗机构信息")
    @PostMapping("/update")
    public ResultJson updateInstitutionInformation(@RequestBody MedicalInstitutionUpdRequest request){
        TMedicalInsititution tmedicalInsititution = new TMedicalInsititution();
        BeanUtils.copyProperties(request,tmedicalInsititution);
        return institutionInformationService.updateInstitutionInformation(tmedicalInsititution).toJson();
    }

    @Operation(summary = "删除定点医疗机构信息")
    @PostMapping("/delete")
    public ResultJson deleteInstitutionInformation(@RequestBody MedicalInstitutionIdRequest request){
        return institutionInformationService.deleteInstitutionInformation(request.getId()).toJson();
    }

    @Operation(summary = "查询定点医疗机构信息")
    @PostMapping("/query")
    public ResultJson queryInstitutionInformation(@RequestBody MedicalInstitutionListRequest request){
        return institutionInformationService.queryInstitutionInformation(request.getPageNum(), request.getPageSize(), request.getSorts(), request.getConditions()).toJson();
    }
}
