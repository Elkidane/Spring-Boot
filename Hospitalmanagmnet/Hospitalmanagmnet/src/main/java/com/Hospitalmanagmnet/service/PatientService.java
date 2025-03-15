package com.Hospitalmanagmnet.service;

import com.Hospitalmanagmnet.model.Patient;
import com.Hospitalmanagmnet.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;



    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);

    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        if (patientRepository.existsById(id)) {
            updatedPatient.setId(id);
            return patientRepository.save(updatedPatient);
        }
        return null;
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
