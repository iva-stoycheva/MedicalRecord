package com.java.medical_record_project.data.dto;

import com.java.medical_record_project.data.entity.Appointment;
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
public class UpdateDiagnoseDTO {
    private long id;
    private String name;
    private Set<Appointment> appointments;
    private int deleted;
}
