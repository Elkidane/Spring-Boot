package com.employee.controller;

import com.employee.dto.ResponseDataDto;
import com.employee.dto.ResponseDto;
import com.employee.dto.ResponseListDto;
import com.employee.model.Address;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import com.employee.exception.ApiRequestException;


import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping("/login")
    public String login(@RequestBody Employee employee){
       return employeeService.verify(employee);
    }

    @PostMapping("/save")
    public ResponseDto saveEmployee(@RequestBody Employee employee) {

        return employeeService.saveEmployee(employee);
    }
    @GetMapping
     public ResponseListDto getAllEmployees() {
        return employeeService.getAllEmployees();
    }

      @GetMapping("/addresses")
  public ResponseListDto getAllAddresses(){
    return employeeService.getAllAddresses();
 }
    @GetMapping("/search/{name}")
    public Optional<Employee> searchEmployeesByName(@RequestParam String name) {
        return employeeService.findEmployeesByName(name);
    }
     @GetMapping("/{id}")
    public ResponseDataDto getEmployeeById(@PathVariable Long id){

     return employeeService.getEmployeeById(id);

 }
    @GetMapping("employee/{id}")
    public Optional<Address> getAddressById(@PathVariable Long id){
      return employeeService.getAddressById(id);
 }
   @PutMapping("/{id}")
    public ResponseDto updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
     return employeeService.updateEmployee(id, employee);
 }
    @DeleteMapping("/{id}")
    public ResponseDto deleteEmployee(@PathVariable Long id){
       return employeeService.deleteEmployee(id);

    }
    @DeleteMapping("/deleteAll")
    public void deleteEmployees(){

        employeeService.deleteEmployees();
    }


}
