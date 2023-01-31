package com.java.medical_record_project.web.view.model;

import com.java.medical_record_project.data.entity.Appointment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateDiagnoseViewModel {
    @NotBlank
    @Size(min=1, max=50, message="Min 1, Max 50")
    private String name;
    private Set<Appointment> appointments;
}
