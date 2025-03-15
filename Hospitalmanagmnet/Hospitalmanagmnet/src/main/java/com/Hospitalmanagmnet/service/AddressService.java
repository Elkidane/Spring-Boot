package com.Hospitalmanagmnet.service;

import com.Hospitalmanagmnet.model.Address;
import com.Hospitalmanagmnet.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    // Get all addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get an address by ID
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Update an address
    public Address updateAddress(Long id, Address updatedAddress) {
        if (addressRepository.existsById(id)) {
            updatedAddress.setId(id);
            return addressRepository.save(updatedAddress);
        }
        return null;
    }

    // Delete an address by ID
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public void deleteAddressesByPatientId(Long patientId) {
        // Assuming you have a method in AddressRepository to delete addresses by patient ID
        addressRepository.deleteByPatientId(patientId);
    }
}
