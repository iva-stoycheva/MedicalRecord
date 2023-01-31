package com.java.medical_record_project.services;


import com.java.medical_record_project.data.dto.CreateSpecialtyDTO;
import com.java.medical_record_project.data.dto.SpecialtyDTO;
import com.java.medical_record_project.data.dto.UpdateSpecialtyDTO;
import com.java.medical_record_project.data.entity.Specialty;

import java.util.List;

public interface SpecialtyService {
    List<SpecialtyDTO> getSpecialties();
    SpecialtyDTO getSpecialty(long id);
    Specialty createSpecialty(CreateSpecialtyDTO createSpecialtyDTO);
    Specialty updateSpecialty(long id, UpdateSpecialtyDTO specialtyDTO);
    void deleteSpecialty(long id);
}
