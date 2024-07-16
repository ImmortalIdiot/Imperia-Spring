package com.immortalidiot.services.dtos;

public class DealRequestDTO {
    private String contact;
    private String name;
    private String terms;

    protected DealRequestDTO() {}

    public DealRequestDTO(String contact, String name, String terms) {
        this.contact = contact;
        this.name = name;
        this.terms = terms;
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

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}
