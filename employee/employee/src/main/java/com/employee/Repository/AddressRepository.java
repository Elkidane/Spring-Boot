package com.employee.Repository;

import com.employee.model.Address;
import com.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    void deleteByemployeeId(Long employeeId );
}
