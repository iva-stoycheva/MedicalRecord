package com.java.medical_record_project.data.repository;

import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    //use
    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId")
    List<Appointment> findByPatientId(@Param("patientId") Long patientId);
    //use
    Long countByDiagnose(Diagnose diagnose);

    List<Appointment> findAllByDate(LocalDate date);
    List<Appointment> findAllByDiagnose(Diagnose diagnose);
    List<Appointment> findAllByDoctor(Doctor doctor);
    List<Appointment> findAllByPatient(Patient patient);
}
