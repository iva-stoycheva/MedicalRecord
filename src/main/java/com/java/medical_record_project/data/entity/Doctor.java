package com.java.medical_record_project.data.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "doctor")
public class Doctor extends BaseEntity {
    @NotBlank
    @Size(min=1, max=20, message="Min 1, Max 20")
    private String uid;

    @NotBlank
    @Size(min=1, max=50, message="Min 1, Max 50")
    private String name;

    @ManyToOne
    @NotNull
    @JoinColumn(name="specialty_id")
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
}
