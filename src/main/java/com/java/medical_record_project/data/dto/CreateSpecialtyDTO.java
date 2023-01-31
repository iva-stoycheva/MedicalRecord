package com.java.medical_record_project.data.dto;

import com.java.medical_record_project.data.entity.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateSpecialtyDTO {
    private String specialty;
    private Set<Doctor> doctors;
}
