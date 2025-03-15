package com.Hospitalmanagmnet.controller;

import com.Hospitalmanagmnet.model.Patient;
import com.Hospitalmanagmnet.model.Address;
import com.Hospitalmanagmnet.service.PatientService;
import com.Hospitalmanagmnet.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
     AddressService addressService;

    @PostMapping("/save")
    public Patient savePatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }
    @PostMapping("/address/save")
    public Address saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);  // Corrected to use addressService
    }
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();  // Use addressService to get all addresses
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }
    @GetMapping("/addresses/{id}")
    public Optional<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);  // Use addressService to get address by ID
    }
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }
    @PutMapping("/addresses/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);  // Use addressService to update address
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
    @DeleteMapping("/addresses/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);  // Use addressService to delete address
    }
}
