package com.java.medical_record_project.data.repository;

import com.java.medical_record_project.data.entity.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
}
