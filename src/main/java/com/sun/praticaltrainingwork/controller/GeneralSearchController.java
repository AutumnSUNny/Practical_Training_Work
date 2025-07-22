package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.People;
import com.sun.praticaltrainingwork.domain.DTO.QueryPeopleAndUnitsRequest;
import com.sun.praticaltrainingwork.domain.DTO.QueryVisitsInfoRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.MedicalInstitutionService;
import com.sun.praticaltrainingwork.service.PeopleService;
import com.sun.praticaltrainingwork.service.personalVisitInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "综合查询",description = "医疗人员费用查询")
@RequestMapping("/generalSearch")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GeneralSearchController {

    private final PeopleService personService;
    private final personalVisitInfoService personalVisitInfoService;

    //查询人员信息及对应的单位信息
    @Operation(description = "查询人员信息及对应的单位信息,需传入身份证号")
    @RequestMapping("/queryPeopleAndUnits")
    public ResultJson queryPeopleAndUnits(@RequestBody QueryPeopleAndUnitsRequest request){
        return personService.queryPeopleAndUnits(request.getID()).toJson();
    }

//    //查询人员就诊信息
//    @Operation(description = "查询人员就诊信息,需传入就诊id")
//    @RequestMapping("/queryVisitsInfo")
//    public ResultJson queryVisitsInfo(@RequestBody QueryVisitsInfoRequest request){
//        return personalVisitInfoService.queryVisitsInfo(request.getID()).toJson();
//    }
//
//    // 查询处方明细
//    @Operation(description = "查询处方明细,需传入住院号")
//    @RequestMapping("/queryPrescriptionDetail")
//    public ResultJson queryPrescriptionDetail(@RequestBody QueryPeopleAndUnitsRequest request){
//        return personalVisitInfoService.queryPrescriptionDetail(request.getID()).toJson();
//    }


}
