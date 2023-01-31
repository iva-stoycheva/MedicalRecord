package com.java.medical_record_project.data.dto;

import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Patient;
import com.java.medical_record_project.data.entity.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDTO {
    private long id;
    private String uid;
    private String name;
    private Specialty specialty;
    private Set<Patient> patients;
    private Set<Appointment> appointments;
    private int deleted;
}
