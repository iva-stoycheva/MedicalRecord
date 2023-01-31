package com.java.medical_record_project.web.api;

import com.java.medical_record_project.data.dto.*;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Specialty;
import com.java.medical_record_project.data.repository.SpecialtyRepository;
import com.java.medical_record_project.services.DoctorService;
import com.java.medical_record_project.services.SpecialtyService;
import com.java.medical_record_project.web.view.model.CreateDoctorViewModel;
import com.java.medical_record_project.web.view.model.UpdateDoctorViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DoctorAPIController {
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/doctor")
    public List<DoctorDTO> getDoctors() {
        return doctorService.getDoctors();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/doctor/{id}")
    public DoctorDTO getDoctor(@PathVariable("id") int id) {
        return doctorService.getDoctor(id);
    }

    @PostMapping(value = "/api/doctor")
    public Doctor createDoctor(@RequestBody CreateDoctorViewModel doctor){
        return doctorService.createDoctor(modelMapper.map(doctor, CreateDoctorDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/doctor/{id}")
    public Doctor updateDoctor(@PathVariable("id") long id, @RequestBody UpdateDoctorViewModel doctor) {
        return doctorService.updateDoctor(id, modelMapper.map(doctor, UpdateDoctorDTO.class));
    }

    @RequestMapping("/api/doctor/names/{name}")
    public List<Doctor> findAllByName(@PathVariable String name) {
        return doctorService.findAllByName(name);
    }

    @RequestMapping("/api/doctor/specialties/{id}")
    public List<Doctor> findAllBySpecialty(@PathVariable Specialty id) {
        return doctorService.findAllBySpecialty(id);
    }

    @DeleteMapping(value = "/api/doctors/{id}")
    public void deleteDoctor(@PathVariable long id){
        doctorService.deleteDoctor(id);
    }

    //use
    @GetMapping("/{id}/patients/count")
    public long countPatientsByDoctor(@PathVariable long id) {
        return doctorService.countPatientsByDoctor(id);
    }

    //use
    @GetMapping("/{id}/appointments/count")
    public long countAppointmentsByDoctor(@PathVariable long id) {
        return doctorService.countAppointmentsByDoctor(id);
    }
}
