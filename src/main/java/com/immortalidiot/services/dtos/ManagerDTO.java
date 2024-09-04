package com.immortalidiot.services.dtos;

public class ManagerDTO {
    private String code;
    private String phoneNumber;

    protected ManagerDTO() {}

    public ManagerDTO(String code, String phoneNumber) {
        this.code = code;
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
