package com.immortalidiot.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class City {
    private String name;

    private String area;

    private List<Cultist> cultists;

    public City(String name, String area) {
        this.name = name;
        this.area = area;
    }

    public City() {}

    @Id
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

    @OneToMany(mappedBy = "city")
    public List<Cultist> getCultists() {
        return cultists;
    }

    public void setCultists(List<Cultist> cultists) {
        this.cultists = cultists;
    }
}
