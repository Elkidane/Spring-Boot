package com.employee.dto;

import com.employee.model.Employee;

public class ResponseDataDto {
    private String desc;
    private boolean status;
    private Employee employee;

    public String getDesc() {

        return desc;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
