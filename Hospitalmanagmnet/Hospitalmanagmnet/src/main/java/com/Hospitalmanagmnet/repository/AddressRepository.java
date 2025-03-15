package com.Hospitalmanagmnet.repository;

import com.Hospitalmanagmnet.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long>{
    void deleteByPatientId(Long patientId);

}
