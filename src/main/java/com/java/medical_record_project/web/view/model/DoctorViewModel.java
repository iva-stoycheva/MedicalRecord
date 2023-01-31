package com.java.medical_record_project.web.view.model;

import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Patient;
import com.java.medical_record_project.data.entity.Specialty;
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
public class DoctorViewModel {
    private int id;

    @NotBlank
    @Size(min=1, max=10, message="Min 1, Max 10")
    private String uid;

    @NotBlank
    @Size(min=1, max=50, message="Min 1, Max 50")
    private String name;

    private Specialty specialty;
    private Set<Patient> patients;
    private Set<Appointment> appointments;
}
