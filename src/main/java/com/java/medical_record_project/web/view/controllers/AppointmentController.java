package com.java.medical_record_project.web.view.controllers;

import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.repository.DiagnoseRepository;
import com.java.medical_record_project.data.repository.DoctorRepository;
import com.java.medical_record_project.data.repository.PatientRepository;
import com.java.medical_record_project.web.view.model.AppointmentViewModel;
import com.java.medical_record_project.web.view.model.CreateAppointmentViewModel;
import com.java.medical_record_project.web.view.model.UpdateAppointmentViewModel;
import com.java.medical_record_project.data.dto.AppointmentDTO;
import com.java.medical_record_project.data.dto.CreateAppointmentDTO;
import com.java.medical_record_project.data.dto.UpdateAppointmentDTO;
import com.java.medical_record_project.services.AppointmentService;
import com.java.medical_record_project.web.view.model.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DiagnoseRepository diagnoseRepository;
    private final ModelMapper modelMapper;


    @GetMapping
    public String getAppointments(Model model) {
        final List<AppointmentViewModel> appointments = appointmentService.getAppointments()
                .stream().map(this::convertToAppointmentViewModel)
                .collect(Collectors.toList());
        model.addAttribute("appointments", appointments);
        return "/appointments/appointments.html";
    }

    @GetMapping("/create-appointment")
    public String showCreateAppointmentForm(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("diagnoses", diagnoseRepository.findAll());
        model.addAttribute("appointment", new CreateAppointmentViewModel());
        return "/appointments/create-appointment";
    }

    @PostMapping("/create")
    public String createAppointment(@Valid @ModelAttribute("appointment") AppointmentViewModel appointmentViewModel,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/appointments/create-appointment";
        }
        appointmentService.createAppointment(modelMapper.map(appointmentViewModel, CreateAppointmentDTO.class));
        return "redirect:/appointments";
    }

    @GetMapping("/edit-appointment/{id}")
    public String showEditAppointmentForm(Model model, @PathVariable Long id) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("diagnoses", diagnoseRepository.findAll());
        model.addAttribute("appointment", modelMapper.map(appointmentService.getAppointment(id),
                UpdateAppointmentViewModel.class));
        return "/appointments/edit-appointment";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable long id, @Valid @ModelAttribute("appointment") UpdateAppointmentViewModel
            appointmentViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/appointments/edit-appointment";
        }
        appointmentService.updateAppointment(id, modelMapper.map(appointmentViewModel, UpdateAppointmentDTO.class));
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }

    private AppointmentViewModel convertToAppointmentViewModel(AppointmentDTO appointmentDTO) {
        return modelMapper.map(appointmentDTO, AppointmentViewModel.class);
    }

    //use
    @GetMapping("/appointments/{parentId}")
    public String countPatientsByDoctor(@PathVariable long patientId, Model model) {
        List<Appointment> appointments = appointmentService.findByPatientId(patientId);
        model.addAttribute("appointments", appointments);
        model.addAttribute("number", "NumberOfPatientsWithGP");
        return "appointments";
    }

    //use
    @GetMapping("/count/{diagnose}")
    public String countPatientsByDiagnose(@PathVariable Diagnose diagnose, Model model) {
        long patientCount = appointmentService.countByDiagnose(diagnose);
        model.addAttribute("diagnose", diagnose);
        model.addAttribute("patientCount", patientCount);
        return "patientCount";
    }
}
