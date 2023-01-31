package com.java.medical_record_project.web.view.controllers;

import com.java.medical_record_project.web.view.model.CreateSpecialtyViewModel;
import com.java.medical_record_project.web.view.model.SpecialtyViewModel;
import com.java.medical_record_project.web.view.model.UpdateSpecialtyViewModel;
import com.java.medical_record_project.data.dto.CreateSpecialtyDTO;
import com.java.medical_record_project.data.dto.SpecialtyDTO;
import com.java.medical_record_project.data.dto.UpdateSpecialtyDTO;
import com.java.medical_record_project.services.SpecialtyService;
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
@RequestMapping("/specialties")
public class SpecialtyController {
    private final SpecialtyService specialtyService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getSpecialties(Model model) {
        final List<SpecialtyViewModel> specialties = specialtyService.getSpecialties()
                .stream().map(this::convertToSpecialtyViewModel)
                .collect(Collectors.toList());
        model.addAttribute("specialties", specialties);
        return "/specialties/specialties.html";
    }

    @GetMapping("/create-specialty")
    public String showCreateSpecialtyForm(Model model) {
        model.addAttribute("specialty", new CreateSpecialtyViewModel());
        return "/specialties/create-specialty";
    }

    @PostMapping("/create")
    public String createSpecialty(@Valid @ModelAttribute("specialty") SpecialtyViewModel specialtyViewModel,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/specialties/create-specialty";
        }
        specialtyService.createSpecialty(modelMapper.map(specialtyViewModel, CreateSpecialtyDTO.class));
        return "redirect:/specialties";
    }

    @GetMapping("/edit-specialty/{id}")
    public String showEditSpecialtyForm(Model model, @PathVariable Long id) {
        model.addAttribute("specialty", modelMapper.map(specialtyService.getSpecialty(id),
                UpdateSpecialtyViewModel.class));
        return "/specialties/edit-specialty";
    }

    @PostMapping("/update/{id}")
    public String updateSpecialty(@PathVariable long id, @Valid @ModelAttribute("specialty") UpdateSpecialtyViewModel
            specialtyViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/specialties/edit-specialty";
        }
        specialtyService.updateSpecialty(id, modelMapper.map(specialtyViewModel, UpdateSpecialtyDTO.class));
        return "redirect:/specialties";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        specialtyService.deleteSpecialty(id);
        return "redirect:/specialties";
    }

    private SpecialtyViewModel convertToSpecialtyViewModel(SpecialtyDTO specialtyDTO) {
        return modelMapper.map(specialtyDTO, SpecialtyViewModel.class);
    }
}
