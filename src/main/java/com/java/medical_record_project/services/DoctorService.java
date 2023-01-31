package com.java.medical_record_project.services;

import com.java.medical_record_project.data.dto.CreateDoctorDTO;
import com.java.medical_record_project.data.dto.DoctorDTO;
import com.java.medical_record_project.data.dto.UpdateDoctorDTO;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Specialty;

import java.util.List;

public interface DoctorService {
    //use
    long countPatientsByDoctor(long doctor_id);
    //use
    long countAppointmentsByDoctor(long doctor_id);

    List<DoctorDTO> getDoctors();
    DoctorDTO getDoctor(long id);
    List<Doctor> findAllByName(String name);
    List<Doctor> findAllBySpecialty(Specialty specialty);
    Doctor createDoctor(CreateDoctorDTO createDoctorDTO);
    Doctor updateDoctor(long id, UpdateDoctorDTO updateDoctorDTO);
    void deleteDoctor(long id);
}
