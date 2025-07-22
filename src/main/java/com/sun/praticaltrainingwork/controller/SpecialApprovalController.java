package com.sun.praticaltrainingwork.controller;

import com.sun.praticaltrainingwork.domain.DO.TSpecialApproval;
import com.sun.praticaltrainingwork.domain.DTO.SpecialApprovalAddRequest;
import com.sun.praticaltrainingwork.domain.DTO.SpecialApprovalIdRequest;
import com.sun.praticaltrainingwork.domain.DTO.SpecialApprovalListRequest;
import com.sun.praticaltrainingwork.domain.DTO.SpecialApprovalUpdRequest;
import com.sun.praticaltrainingwork.domain.VO.Restful;
import com.sun.praticaltrainingwork.domain.VO.Restful.ResultJson;
import com.sun.praticaltrainingwork.service.SpecialApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "特殊审批", description = "特殊审批相关接口")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping("/specialApproval")
public class SpecialApprovalController {
    private final SpecialApprovalService specialApprovalService;


    //增加
    @Operation(description = "增加特殊审批")
    @PostMapping("/add")
    public ResultJson addSpecialApproval(@RequestBody SpecialApprovalAddRequest req) {
        TSpecialApproval tSpecialApproval = new TSpecialApproval();
        BeanUtils.copyProperties(req, tSpecialApproval);
        return specialApprovalService.addSpecialApproval(tSpecialApproval).toJson();
    }

    //删除
    @Operation(description = "删除特殊审批")
    @PostMapping("/delete")
    public ResultJson deleteSpecialApproval(@RequestBody SpecialApprovalIdRequest req) {
        return specialApprovalService.deleteSpecialApproval(req.getId()).toJson();
    }

    //修改
    @Operation(description = "修改特殊审批")
    @PostMapping("/update")
    public ResultJson updateSpecialApproval(@RequestBody SpecialApprovalUpdRequest req) {
        TSpecialApproval tSpecialApproval = new TSpecialApproval();
        BeanUtils.copyProperties(req, tSpecialApproval);
        return specialApprovalService.updateSpecialApproval(tSpecialApproval).toJson();
    }

    //查询
    @Operation(description = "查询特殊审批")
    @PostMapping("/query")
    public ResultJson querySpecialApproval(@RequestBody SpecialApprovalListRequest req) {
        return specialApprovalService.querySpecialApproval(req.getPageNum(), req.getPageSize(), req.getSorts(), req.getConditions()).toJson();

    }
}
