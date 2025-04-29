package com.employee.service;

import com.employee.Repository.EmployeeRepository;
import com.employee.model.Employee;
import com.employee.model.EmployeePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        return new EmployeePrincipal(employee);
    }
}
