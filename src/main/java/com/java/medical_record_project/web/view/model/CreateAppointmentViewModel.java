package com.java.medical_record_project.web.view.model;

import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateAppointmentViewModel {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Diagnose diagnose;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @NotNull
    private int sick_leave_days;

    @NotNull
    @Size(min=1, max=50, message="Min 1, Max 50")
    private String medicament;

    @NotNull
    private Doctor doctor;

    @NotNull
    private Patient patient;
}
