package com.java.medical_record_project.data.dto;

import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PatientDTO {
    private long id;
    private String pin;
    private String name;
    private Boolean is_insurance_paid;
    private Doctor doctor;
    private Set<Appointment> appointments;
    private int deleted;
}
