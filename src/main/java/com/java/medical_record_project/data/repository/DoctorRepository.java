package com.java.medical_record_project.data.repository;

import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    //use
    @Query("SELECT COUNT(p) FROM Patient p WHERE p.doctor.id = :doctor_id")
    long countPatientsByDoctor(@Param("doctor_id") long doctor_id);
    //use
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctor_id")
    long countAppointmentsByDoctor(@Param("doctor_id") long doctor_id);

    List<Doctor> findAllByName(String name);
    List<Doctor> findAllBySpecialty(Specialty specialty);
}
