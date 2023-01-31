package com.java.medical_record_project.services;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.data.entity.Diagnose;

import java.util.List;

public interface DiagnoseService {
    List<DiagnoseDTO> getDiagnoses();
    DiagnoseDTO getDiagnose(long id);
    Diagnose createDiagnose(CreateDiagnoseDTO createDiagnoseDTO);
    Diagnose updateDiagnose(long id, UpdateDiagnoseDTO diagnoseDTO);
    void deleteDiagnose(long id);
}
