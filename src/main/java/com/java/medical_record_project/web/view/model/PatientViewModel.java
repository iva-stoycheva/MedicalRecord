package com.java.medical_record_project.web.view.model;

import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Doctor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PatientViewModel {
    private int id;

    @NotBlank
    @Size(min=10, max=10, message="Min 10, Max 10")
    private String pin;

    @NotBlank
    @Size(min=1, max=50, message="Min 1, Max 50")
    private String name;

    @NotNull
    private Boolean is_insurance_paid;

    private Doctor doctor;
    private Set<Appointment> appointments;
}
