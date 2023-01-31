package com.java.medical_record_project.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "diagnose")
public class Diagnose extends BaseEntity {
    @NotBlank
    @Size(min=1, max=50, message="Min 1, Max 50")
    private String name;

    @OneToMany
    @JsonIgnoreProperties("diagnose")
    private Set<Appointment> appointments;
}
