package com.java.medical_record_project.data.repository;

import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByName(String name);
    List<Patient> findAllByDoctor(Doctor doctor);
}
