package com.java.medical_record_project.services.implementations;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import com.java.medical_record_project.data.repository.PatientRepository;
import com.java.medical_record_project.services.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToPatientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatient(long id) {
        return modelMapper.map(patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Patient ID: " + id)), PatientDTO.class);
    }

    @Override
    public List<Patient> findAllByName(String name) {
        return patientRepository.findAllByName(name);
    }

    @Override
    public List<Patient> findAllByDoctor(Doctor doctor) {
        return patientRepository.findAllByDoctor(doctor);
    }

    @Override
    public Patient createPatient(CreatePatientDTO createPatientDTO) {
        return patientRepository.save(modelMapper.map(createPatientDTO, Patient.class));
    }

    @Override
    public Patient updatePatient(long id, UpdatePatientDTO updatePatientDTO) {
        Patient patient = modelMapper.map(updatePatientDTO, Patient.class);
        patient.setId(id);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }

    private PatientDTO convertToPatientDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }
}
