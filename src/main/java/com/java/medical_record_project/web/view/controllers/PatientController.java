package com.java.medical_record_project.web.view.controllers;

import com.java.medical_record_project.data.repository.DoctorRepository;
import com.java.medical_record_project.web.view.model.CreatePatientViewModel;
import com.java.medical_record_project.web.view.model.PatientViewModel;
import com.java.medical_record_project.web.view.model.UpdatePatientViewModel;
import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.services.PatientService;
import com.java.medical_record_project.web.view.model.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getPatients(Model model) {
        final List<PatientViewModel> patients = patientService.getPatients()
                .stream().map(this::convertToPatientViewModel)
                .collect(Collectors.toList());
        model.addAttribute("patients", patients);
        return "/patients/patients.html";
    }

    @GetMapping("/create-patient")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patient", new CreatePatientViewModel());
        return "/patients/create-patient";
    }

    @PostMapping("/create")
    public String createPatient(@Valid @ModelAttribute("patient") PatientViewModel patientViewModel,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/patients/create-patient";
        }
        patientService.createPatient(modelMapper.map(patientViewModel, CreatePatientDTO.class));
        return "redirect:/patients";
    }

    @GetMapping("/edit-patient/{id}")
    public String showEditPatientForm(Model model, @PathVariable Long id) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patient", modelMapper.map(patientService.getPatient(id),
                UpdatePatientViewModel.class));
        return "/patients/edit-patient";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable long id, @Valid @ModelAttribute("patient") UpdatePatientViewModel
            patientViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/patients/edit-patient";
        }
        patientService.updatePatient(id, modelMapper.map(patientViewModel, UpdatePatientDTO.class));
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    private PatientViewModel convertToPatientViewModel(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, PatientViewModel.class);
    }
}
