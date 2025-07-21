package com.sun.praticaltrainingwork.service;

import com.sun.praticaltrainingwork.domain.DO.TMedicalInsititution;
import com.sun.praticaltrainingwork.domain.DTO.MedicalInstitution.MedicalInstitutionListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.MedicalInstitution.MedicalInstitutionVO;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MedicalInstitutionService {
    Result<Void> addInstitutionInformation(TMedicalInsititution tMedicalInsititution);

    Result<Void> updateInstitutionInformation(TMedicalInsititution tmedicalInsititution);

    Result<Void> deleteInstitutionInformation(Integer id);

    Result<QueryVO<MedicalInstitutionVO>> queryInstitutionInformation(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, MedicalInstitutionListRequest.Conditions conditions);
}
