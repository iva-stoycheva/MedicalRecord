package com.java.medical_record_project.web.view.controllers;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.services.DiagnoseService;
import com.java.medical_record_project.services.SpecialtyService;
import com.java.medical_record_project.web.view.model.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/diagnoses")
public class DiagnoseController {
    private final DiagnoseService diagnoseService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getDiagnoses(Model model) {
        final List<DiagnoseViewModel> diagnoses = diagnoseService.getDiagnoses()
                .stream().map(this::convertToDiagnoseViewModel)
                .collect(Collectors.toList());
        model.addAttribute("diagnoses", diagnoses);
        return "/diagnoses/diagnoses.html";
    }

    @GetMapping("/create-diagnose")
    public String showCreateDiagnoseForm(Model model) {
        model.addAttribute("diagnose", new CreateDiagnoseViewModel());
        return "/diagnoses/create-diagnose";
    }

    @PostMapping("/create")
    public String createDiagnose(@Valid @ModelAttribute("diagnose") DiagnoseViewModel diagnoseViewModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/diagnoses/create-diagnose";
        }
        diagnoseService.createDiagnose(modelMapper.map(diagnoseViewModel, CreateDiagnoseDTO.class));
        return "redirect:/diagnoses";
    }

    @GetMapping("/edit-diagnose/{id}")
    public String showEditDiagnoseForm(Model model, @PathVariable Long id) {
        model.addAttribute("diagnose", modelMapper.map(diagnoseService.getDiagnose(id),
                UpdateDiagnoseViewModel.class));
        return "/diagnoses/edit-diagnose";
    }

    @PostMapping("/update/{id}")
    public String updateDiagnose(@PathVariable long id, @Valid @ModelAttribute("diagnose") UpdateDiagnoseViewModel
            diagnoseViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/diagnoses/edit-diagnose";
        }
        diagnoseService.updateDiagnose(id, modelMapper.map(diagnoseViewModel, UpdateDiagnoseDTO.class));
        return "redirect:/diagnoses";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        diagnoseService.deleteDiagnose(id);
        return "redirect:/diagnoses";
    }

    private DiagnoseViewModel convertToDiagnoseViewModel(DiagnoseDTO diagnoseDTO) {
        return modelMapper.map(diagnoseDTO, DiagnoseViewModel.class);
    }
}
