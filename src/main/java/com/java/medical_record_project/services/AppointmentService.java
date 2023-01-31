package com.java.medical_record_project.services;

import com.java.medical_record_project.data.dto.AppointmentDTO;
import com.java.medical_record_project.data.dto.CreateAppointmentDTO;
import com.java.medical_record_project.data.dto.UpdateAppointmentDTO;
import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AppointmentService {
    //use
    Long countByDiagnose(Diagnose diagnose);
    //use
    List<Appointment> findByPatientId(long patientId);

    List<AppointmentDTO> getAppointments();
    AppointmentDTO getAppointment(long id);
    List<Appointment> findAllByDate(LocalDate date);
    List<Appointment> findAllByDiagnose(Diagnose diagnose);
    List<Appointment> findAllByDoctor(Doctor doctor);
    List<Appointment> findAllByPatient(Patient patient);
    Appointment createAppointment(CreateAppointmentDTO createAppointmentDTO);
    Appointment updateAppointment(long id, UpdateAppointmentDTO updateAppointmentDTO);
    void deleteAppointment(long id);
}
