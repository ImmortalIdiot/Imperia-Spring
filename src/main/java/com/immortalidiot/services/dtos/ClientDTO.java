package com.immortalidiot.services.dtos;

public class ClientDTO {
    private String contact;
    private String name;

    private ClientDTO() {}

    public ClientDTO(String contact, String name) {
        this.contact = contact;
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
