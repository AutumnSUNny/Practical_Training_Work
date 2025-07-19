package com.sun.praticaltrainingwork.controller;


import com.sun.praticaltrainingwork.domain.DO.TDiagnosisProject;
import com.sun.praticaltrainingwork.domain.DTO.DiagnosisAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.DiagnosisIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.DiagnosisListRequest;
import com.sun.praticaltrainingwork.domain.DTO.DiagnosisUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.DiagnosisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "诊疗项目管理")
@RestController
@RequestMapping("/diagnosis")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DiagnosisController {


    private final DiagnosisService diagnosisService;


    @Operation(summary = "添加诊疗项目", description = "添加诊疗项目")
    @PostMapping("/add")
    public ResultJson addDiagnosis(@RequestBody DiagnosisAddRequest request) {
        TDiagnosisProject tdiagnosisproject = new TDiagnosisProject();
        BeanUtils.copyProperties(request, tdiagnosisproject);
        return diagnosisService.addDiagnosis(tdiagnosisproject).toJson();
    }

    @Operation(summary = "删除诊疗项目", description = "删除诊疗项目")
    @PostMapping("/delete")
    public ResultJson deleteDiagnosis(@RequestBody DiagnosisIdRequest request) {
        return diagnosisService.deleteDiagnosis(request.getId()).toJson();
    }

    @Operation(summary = "修改诊疗项目", description = "修改诊疗项目")
    @PostMapping("/update")
    public ResultJson updateDiagnosis(@RequestBody DiagnosisUpdRequest request) {
        TDiagnosisProject tdiagnosisproject = new TDiagnosisProject();
        BeanUtils.copyProperties(request, tdiagnosisproject);
        return diagnosisService.updateDiagnosis(tdiagnosisproject).toJson();
    }

    @Operation(summary = "查询诊疗项目", description = "查询诊疗项目")
    @PostMapping("/query")
    public ResultJson queryDiagnosis(@RequestBody DiagnosisListRequest request) {
        return diagnosisService.queryDiagnosis(request.getPageNum(),request.getPageSize(),request.getSorts(),request.getConditions()).toJson();
    }

}
