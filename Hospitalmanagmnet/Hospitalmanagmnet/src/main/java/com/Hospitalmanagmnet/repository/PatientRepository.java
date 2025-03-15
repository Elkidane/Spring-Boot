package com.Hospitalmanagmnet.repository;

import com.Hospitalmanagmnet.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
