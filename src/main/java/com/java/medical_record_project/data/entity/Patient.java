package com.java.medical_record_project.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends BaseEntity {
    @NotBlank
    @Size(min=10, max=10, message="Min 10, Max 10")
    private String pin;

    @NotBlank
    @Size(min=1, max=50, message="Min 1, Max 50")
    private String name;

    private Boolean is_insurance_paid;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;
}
