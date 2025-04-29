package com.employee.service;

import com.employee.Repository.AddressRepository;
import com.employee.Repository.EmployeeRepository;
import com.employee.dto.ResponseDataDto;
import com.employee.dto.ResponseDto;
import com.employee.dto.ResponseListDto;
import com.employee.model.Address;
import com.employee.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public EmployeeService(JWTService jwtService) {

        this.jwtService = jwtService;
    }

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Transactional
    public ResponseDto saveEmployee(Employee employee) {
        ResponseDto responseDto = new ResponseDto();

        try {
            if (employee.getAddresses() != null && !employee.getAddresses().isEmpty()) {
                for (Address address : employee.getAddresses()) {
                    address.setEmployee(employee);
                }
            }

            employee.setPassword(encoder.encode(employee.getPassword()));
            Employee savedEmployee = employeeRepository.save(employee);
            addressRepository.saveAll(savedEmployee.getAddresses());
            responseDto.setDesc("sucessfully saved");
            responseDto.setStatus(true);
            return responseDto;
        } catch (Exception e) {
            System.out.println(" Exception  :" + e.getMessage());
            responseDto.setDesc("Connection lost");
            responseDto.setStatus(false);
            return responseDto;
        }

    }

    public ResponseListDto getAllEmployees() {
        ResponseListDto responseListDto = new ResponseListDto();

        try {
            List<Employee> employees = employeeRepository.findAll();
            responseListDto.setStatus(true);
            responseListDto.setDesc("Employees fetched successfully");
            responseListDto.setEmployees(employees);
        } catch (Exception e) {
            responseListDto.setStatus(false);
            responseListDto.setDesc("Error fetching employees: " + e.getMessage());
            responseListDto.setEmployees(null);
        }

        return responseListDto;
    }

    public  ResponseListDto getAllAddresses() {
        ResponseListDto responseListDto=new ResponseListDto();
        try{
            List<Address> addresses = addressRepository.findAll();
            responseListDto.setStatus(true);
            responseListDto.setDesc("Addresses fetched successfully");
            responseListDto.setAddresses(addresses);

        }catch(Exception e){
            responseListDto.setStatus(false);
            responseListDto.setDesc("Error fetching Addresses");
            System.out.println("Error :"+e);

        }
        return responseListDto;
    }
    public  ResponseDataDto getEmployeeById(Long id){
        ResponseDataDto responseDto= new ResponseDataDto();

        try{
            if(!employeeRepository.existsById(id)){
                responseDto.setStatus(false);
                responseDto.setDesc("employee with id :"+id +" does not exist");
                return responseDto;
            }
            Optional<Employee> employee=employeeRepository.findById(id);
            responseDto.setEmployee(employee.get());
            responseDto.setDesc("employee does exist");
            responseDto.setStatus(true);
            return responseDto;
        }catch(Exception e){
            System.out.println("Exception :" +e.getMessage());
            responseDto.setStatus(false);
            responseDto.setDesc("Employee with id :"+id+"does not exist");
            return responseDto;
        }

    }
    public Optional<Employee> findEmployeesByName(String name) {

        return employeeRepository.findByName(name);
    }
    public Optional<Address> getAddressById(Long id){

        return addressRepository.findById(id);
    }
    public ResponseDto  updateEmployee(Long id,Employee updateEmployee){
        ResponseDto responseDto=new ResponseDto();
        try{
        if(!employeeRepository.existsById(id)) {
            responseDto.setStatus(false);
            responseDto.setDesc("Employee to update not found");
            return responseDto;

        }
            updateEmployee.setId(id);
            responseDto.setDesc("Employee update successfully");
            responseDto.setStatus(true);
            return responseDto;
        }catch (Exception e){
            System.out.println("Exception :" +e.getMessage());
            responseDto.setDesc("Failed to update employee");
            responseDto.setStatus(false);
            return responseDto;
        }

    }

    public ResponseDto deleteEmployee(Long id) {
        ResponseDto responseDto = new ResponseDto();


        try {
            if (!employeeRepository.existsById(id)) {
                responseDto.setStatus(false);
                responseDto.setDesc("Employee not found");
                return responseDto;
            }
            employeeRepository.deleteById(id);
            responseDto.setDesc("employee deleted");
            responseDto.setStatus(true);
            return responseDto;
        } catch (Exception e){//if in case the database is down
            System.out.println("Exception : "+ e.getMessage());
            responseDto.setStatus(false);
            responseDto.setDesc("Cannot delete employee");
            return responseDto;
        }
    }
   public void deleteEmployees(){

        employeeRepository.deleteAll();
   }

    public String verify(Employee employee) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getName(),employee.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(employee.getName());

        return "fail";
    }
}

