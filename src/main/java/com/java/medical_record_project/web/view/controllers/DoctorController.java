package com.java.medical_record_project.web.view.controllers;

import com.java.medical_record_project.data.entity.Specialty;
import com.java.medical_record_project.data.repository.SpecialtyRepository;
import com.java.medical_record_project.services.SpecialtyService;
import com.java.medical_record_project.web.view.model.CreateDoctorViewModel;
import com.java.medical_record_project.web.view.model.DoctorViewModel;
import com.java.medical_record_project.web.view.model.UpdateDoctorViewModel;
import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.services.DoctorService;
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
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getDoctors(Model model) {
        final List<DoctorViewModel> doctors = doctorService.getDoctors()
                .stream().map(this::convertToDoctorViewModel)
                .collect(Collectors.toList());
        model.addAttribute("doctors", doctors);
        return "/doctors/doctors.html";
    }

    @GetMapping("/create-doctor")
    public String showCreateDoctorForm(Model model) {
        model.addAttribute("specialties", specialtyRepository.findAll());
        model.addAttribute("doctor", new CreateDoctorViewModel());
        return "/doctors/create-doctor";
    }

    @PostMapping("/create")
    public String createDoctor(@Valid @ModelAttribute("doctor") DoctorViewModel doctorViewModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/doctors/create-doctor";
        }
        doctorService.createDoctor(modelMapper.map(doctorViewModel, CreateDoctorDTO.class));
        return "redirect:/doctors";
    }

    @GetMapping("/edit-doctor/{id}")
    public String showEditDoctorForm(Model model, @PathVariable Long id) {
        model.addAttribute("specialties", specialtyRepository.findAll());
        model.addAttribute("doctor", modelMapper.map(doctorService.getDoctor(id),
                UpdateDoctorViewModel.class));
        return "/doctors/edit-doctor";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable long id, @Valid @ModelAttribute("doctor") UpdateDoctorViewModel
            doctorViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/doctors/edit-doctor";
        }
        doctorService.updateDoctor(id, modelMapper.map(doctorViewModel, UpdateDoctorDTO.class));
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

    private DoctorViewModel convertToDoctorViewModel(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, DoctorViewModel.class);
    }

    //use
    @GetMapping("/{id}/appointments/count")
    public String countAppointmentsByDoctor(@PathVariable long id, Model model) {
        long appointmentCount = doctorService.countAppointmentsByDoctor(id);
        model.addAttribute("id", id);
        model.addAttribute("appointmentCount", appointmentCount);
        return "appointmentCount";
    }

    //use
    @GetMapping("/{id}/patients/count")
    public String countPatientsByDoctor(@PathVariable long id, Model model) {
        System.out.println(id);
        long patientCount = doctorService.countPatientsByDoctor(id);
        System.out.println(patientCount);
        model.addAttribute("id", id);
        model.addAttribute("patientCount", patientCount);
        return "patientCount";
    }
}
