package com.java.medical_record_project.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "specialty")
public class Specialty extends BaseEntity {
    @NotBlank
    @Size(min=1, max=40, message="Min 1, Max 40")
    private String specialty;

    @OneToMany(mappedBy = "specialty")
    private Set<Doctor> doctors;
}
