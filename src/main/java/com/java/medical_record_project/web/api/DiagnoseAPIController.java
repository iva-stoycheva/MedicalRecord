package com.java.medical_record_project.web.api;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.entity.Specialty;
import com.java.medical_record_project.services.DiagnoseService;
import com.java.medical_record_project.services.SpecialtyService;
import com.java.medical_record_project.web.view.model.CreateDiagnoseViewModel;
import com.java.medical_record_project.web.view.model.CreateSpecialtyViewModel;
import com.java.medical_record_project.web.view.model.UpdateDiagnoseViewModel;
import com.java.medical_record_project.web.view.model.UpdateSpecialtyViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DiagnoseAPIController {
    private final DiagnoseService diagnoseService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/diagnose")
    public List<DiagnoseDTO> getDiagnoses() {
        return diagnoseService.getDiagnoses();
    }

    @RequestMapping("/api/diagnose/{id}")
    public DiagnoseDTO getDiagnose(@PathVariable("id") int id) {
        return diagnoseService.getDiagnose(id);
    }

    @PostMapping(value = "/api/diagnose")
    public Diagnose createDiagnose(@RequestBody CreateDiagnoseViewModel diagnose){
        return diagnoseService.createDiagnose(modelMapper.map(diagnose, CreateDiagnoseDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/diagnose/{id}")
    public Diagnose updateDiagnose(@PathVariable("id") long id, @RequestBody UpdateDiagnoseViewModel diagnose) {
        return diagnoseService.updateDiagnose(id, modelMapper.map(diagnose, UpdateDiagnoseDTO.class));
    }

    @DeleteMapping(value = "/api/diagnoses/{id}")
    public void deleteDiagnose(@PathVariable long id){
        diagnoseService.deleteDiagnose(id);
    }
}
