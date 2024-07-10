package com.immortalidiot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class City {
    private String name;

    private String area;

    public City(String name, String area) {
        this.name = name;
        this.area = area;
    }

    @OneToMany
    @JoinColumn(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "area", nullable = false)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
