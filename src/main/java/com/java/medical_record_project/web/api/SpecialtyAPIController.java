package com.java.medical_record_project.web.api;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.data.entity.Specialty;
import com.java.medical_record_project.services.SpecialtyService;
import com.java.medical_record_project.web.view.model.CreateSpecialtyViewModel;
import com.java.medical_record_project.web.view.model.UpdateSpecialtyViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SpecialtyAPIController {
    private final SpecialtyService specialtyService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/specialty")
    public List<SpecialtyDTO> getSpecialties() {
        return specialtyService.getSpecialties();
    }

    @RequestMapping("/api/specialty/{id}")
    public SpecialtyDTO getSpecialty(@PathVariable("id") int id) {
        return specialtyService.getSpecialty(id);
    }

    @PostMapping(value = "/api/specialty")
    public Specialty createSpecialty(@RequestBody CreateSpecialtyViewModel specialty){
        return specialtyService.createSpecialty(modelMapper.map(specialty, CreateSpecialtyDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/specialty/{id}")
    public Specialty updateSpecialty(@PathVariable("id") long id, @RequestBody UpdateSpecialtyViewModel specialty) {
        return specialtyService.updateSpecialty(id, modelMapper.map(specialty, UpdateSpecialtyDTO.class));
    }

    @DeleteMapping(value = "/api/specialties/{id}")
    public void deleteSpecialty(@PathVariable long id){
        specialtyService.deleteSpecialty(id);
    }
}
