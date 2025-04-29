package com.employee.dto;

import jdk.jfr.DataAmount;


public class ResponseDto {
    private String desc;
    private boolean status;

    public String getDesc() {
        return desc;
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
