package com.java.medical_record_project.web.api;

import com.java.medical_record_project.data.dto.AppointmentDTO;
import com.java.medical_record_project.data.dto.CreateAppointmentDTO;
import com.java.medical_record_project.data.dto.UpdateAppointmentDTO;
import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import com.java.medical_record_project.services.AppointmentService;
import com.java.medical_record_project.web.view.model.CreateAppointmentViewModel;
import com.java.medical_record_project.web.view.model.UpdateAppointmentViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class AppointmentAPIController {
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/appointment")
    public List<AppointmentDTO> getAppointments() {
        return appointmentService.getAppointments();
    }

    @RequestMapping("/api/appointment/{id}")
    public AppointmentDTO getAppointment(@PathVariable("id") int id) {
        return appointmentService.getAppointment(id);
    }

    @PostMapping(value = "/api/appointment")
    public Appointment createAppointment(@RequestBody CreateAppointmentViewModel appointment){
        return appointmentService.createAppointment(modelMapper.map(appointment, CreateAppointmentDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/appointment/{id}")
    public Appointment updateAppointment(@PathVariable("id") long id, @RequestBody UpdateAppointmentViewModel appointment) {
        return appointmentService.updateAppointment(id, modelMapper.map(appointment, UpdateAppointmentDTO.class));
    }

    @RequestMapping("/api/appointment/dates/{date}")
    public List<Appointment> findAllByDate(@PathVariable LocalDate date) {
        return appointmentService.findAllByDate(date);
    }

    @RequestMapping("/api/appointment/diagnoses/{diagnose}")
    public List<Appointment> findAllByDiagnose(@PathVariable Diagnose diagnose) {
        return appointmentService.findAllByDiagnose(diagnose);
    }

    @RequestMapping("/api/appointment/patient/{id}")
    public List<Appointment> findAllByPatient(@PathVariable Patient patient) {
        return appointmentService.findAllByPatient(patient);
    }

    @RequestMapping("/api/appointment/doctor/{id}")
    public List<Appointment> findAllByDoctor(@PathVariable Doctor doctor) {
        return appointmentService.findAllByDoctor(doctor);
    }

    @DeleteMapping(value = "/api/appointment/{id}")
    public void deleteAppointment(@PathVariable long id){
        appointmentService.deleteAppointment(id);
    }

    //use
    @GetMapping("/count/{diagnose}")
    public Long countPatientsByDiagnose(@PathVariable Diagnose diagnose) {
        return appointmentService.countByDiagnose(diagnose);
    }

    //use
    @GetMapping("/appointments/{parentId}")
    public List<Appointment> countPatientsByDoctor(@PathVariable long parentId) {
        return appointmentService.findByPatientId(parentId);
    }
}
