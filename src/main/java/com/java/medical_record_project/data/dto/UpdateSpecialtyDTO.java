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
public class UpdateSpecialtyDTO {
    private long id;
    private String specialty;
    private Set<Doctor> doctors;
    private int deleted;
}
