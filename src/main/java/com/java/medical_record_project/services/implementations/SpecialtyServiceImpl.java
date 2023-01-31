package com.java.medical_record_project.services.implementations;

import com.java.medical_record_project.data.dto.CreateSpecialtyDTO;
import com.java.medical_record_project.data.dto.SpecialtyDTO;
import com.java.medical_record_project.data.dto.UpdateSpecialtyDTO;
import com.java.medical_record_project.data.entity.Specialty;
import com.java.medical_record_project.data.repository.SpecialtyRepository;
import com.java.medical_record_project.services.SpecialtyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SpecialtyDTO> getSpecialties() {
        return specialtyRepository.findAll().stream()
                .map(this::convertToSpecialtyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO getSpecialty(long id) {
        return modelMapper.map(specialtyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Specialty ID: " + id)), SpecialtyDTO.class);
    }

    @Override
    public Specialty createSpecialty(CreateSpecialtyDTO createSpecialtyDTO) {
        return specialtyRepository.save(modelMapper.map(createSpecialtyDTO, Specialty.class));
    }

    @Override
    public Specialty updateSpecialty(long id, UpdateSpecialtyDTO updateSpecialtyDTO) {
        Specialty specialty = modelMapper.map(updateSpecialtyDTO, Specialty.class);
        specialty.setId(id);
        return specialtyRepository.save(specialty);
    }

    @Override
    public void deleteSpecialty(long id) {
        specialtyRepository.deleteById(id);
    }

    private SpecialtyDTO convertToSpecialtyDTO(Specialty specialty) {
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }
}
