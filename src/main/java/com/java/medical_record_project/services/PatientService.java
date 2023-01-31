package com.java.medical_record_project.services;

import com.java.medical_record_project.data.dto.CreatePatientDTO;
import com.java.medical_record_project.data.dto.PatientDTO;
import com.java.medical_record_project.data.dto.UpdatePatientDTO;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getPatients();
    PatientDTO getPatient(long id);
    List<Patient> findAllByName(String name);
    List<Patient> findAllByDoctor(Doctor doctor);
    Patient createPatient(CreatePatientDTO createPatientDTO);
    Patient updatePatient(long id, UpdatePatientDTO updatePatientDTO);
    void deletePatient(long id);
}
