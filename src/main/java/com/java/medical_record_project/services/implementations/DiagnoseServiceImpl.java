package com.java.medical_record_project.services.implementations;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.entity.Specialty;
import com.java.medical_record_project.data.repository.DiagnoseRepository;
import com.java.medical_record_project.data.repository.SpecialtyRepository;
import com.java.medical_record_project.services.DiagnoseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiagnoseServiceImpl implements DiagnoseService {
    private final DiagnoseRepository diagnoseRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DiagnoseDTO> getDiagnoses() {
        return diagnoseRepository.findAll().stream()
                .map(this::convertToDiagnoseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiagnoseDTO getDiagnose(long id) {
        return modelMapper.map(diagnoseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Diagnose ID: " + id)), DiagnoseDTO.class);
    }

    @Override
    public Diagnose createDiagnose(CreateDiagnoseDTO createDiagnoseDTO) {
        return diagnoseRepository.save(modelMapper.map(createDiagnoseDTO, Diagnose.class));
    }

    @Override
    public Diagnose updateDiagnose(long id, UpdateDiagnoseDTO updateDiagnoseDTO) {
        Diagnose diagnose = modelMapper.map(updateDiagnoseDTO, Diagnose.class);
        diagnose.setId(id);
        return diagnoseRepository.save(diagnose);
    }

    @Override
    public void deleteDiagnose(long id) {
        diagnoseRepository.deleteById(id);
    }

    private DiagnoseDTO convertToDiagnoseDTO(Diagnose diagnose) {
        return modelMapper.map(diagnose, DiagnoseDTO.class);
    }
}
