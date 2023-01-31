package com.java.medical_record_project.web.api;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import com.java.medical_record_project.services.PatientService;
import com.java.medical_record_project.web.view.model.CreatePatientViewModel;
import com.java.medical_record_project.web.view.model.UpdatePatientViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientAPIController {
    private final PatientService patientService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/patient")
    public List<PatientDTO> getPatients() {
        return patientService.getPatients();
    }

    @RequestMapping("/api/patient/{id}")
    public PatientDTO getPatient(@PathVariable("id") int id) {
        return patientService.getPatient(id);
    }

    @RequestMapping("/api/patient/names/{name}")
    public List<Patient> findAllByName(@PathVariable String name) {
        return patientService.findAllByName(name);
    }

    @RequestMapping("/api/patients/doctor/{id}")
    public List<Patient> findAllByDoctor(@PathVariable Doctor id) {
        return patientService.findAllByDoctor(id);
    }

    @PostMapping(value = "/api/patient")
    public Patient createPatient(@RequestBody CreatePatientViewModel patient){
        return patientService.createPatient(modelMapper.map(patient, CreatePatientDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/patients/{id}")
    public Patient updatePatient(@PathVariable("id") long id, @RequestBody UpdatePatientViewModel patient) {
        return patientService.updatePatient(id, modelMapper.map(patient, UpdatePatientDTO.class));
    }

    @DeleteMapping(value = "/api/patients/{id}")
    public void deletePatient(@PathVariable long id){
        patientService.deletePatient(id);
    }
}
