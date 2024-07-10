package com.immortalidiot.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    private String contact;
    private String name;
    private List<Deal> deals;

    public Client(String contact, String name) {
        this.contact = contact;
        this.name = name;
    }

    public Client() {}

    @Id
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "client", targetEntity = Deal.class)
    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
}

