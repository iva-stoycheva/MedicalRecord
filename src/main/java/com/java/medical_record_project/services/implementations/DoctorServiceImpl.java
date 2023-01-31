package com.java.medical_record_project.services.implementations;

import com.java.medical_record_project.data.dto.CreateDoctorDTO;
import com.java.medical_record_project.data.dto.DoctorDTO;
import com.java.medical_record_project.data.dto.UpdateDoctorDTO;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Specialty;
import com.java.medical_record_project.data.repository.DoctorRepository;
import com.java.medical_record_project.services.DoctorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<DoctorDTO> getDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctor(long id) {
        return modelMapper.map(doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Doctor ID: " + id)), DoctorDTO.class);
    }

    @Override
    public List<Doctor> findAllByName(String name) {
        return doctorRepository.findAllByName(name);
    }

    @Override
    public List<Doctor> findAllBySpecialty(Specialty specialty) {
        return doctorRepository.findAllBySpecialty(specialty);
    }

    @Override
    public Doctor createDoctor(CreateDoctorDTO createDoctorDTO) {
        return doctorRepository.save(modelMapper.map(createDoctorDTO, Doctor.class));
    }

    @Override
    public Doctor updateDoctor(long id, UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctor = modelMapper.map(updateDoctorDTO, Doctor.class);
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDTO convertToDoctorDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    //use
    @Override
    public long countPatientsByDoctor(long doctor_id) {
        return doctorRepository.countPatientsByDoctor(doctor_id);
    }

    //use
    @Override
    public long countAppointmentsByDoctor(long doctor_id) {
        return doctorRepository.countAppointmentsByDoctor(doctor_id);
    }
}
