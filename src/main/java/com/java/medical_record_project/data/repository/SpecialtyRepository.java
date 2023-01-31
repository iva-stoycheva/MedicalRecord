package com.java.medical_record_project.data.repository;

import com.java.medical_record_project.data.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
