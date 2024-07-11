package com.immortalidiot.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "manager")
public class Manager {
    private String code;
    private String phoneNumber;
    private Set<Deal> deals;

    public Manager(String code, String phoneNumber) {
        this.code = code;
        this.phoneNumber = phoneNumber;
    }

    public Manager() {}

    @Id
    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "phone_number", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(mappedBy = "manager", targetEntity = Deal.class)
    public Set<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }
}
