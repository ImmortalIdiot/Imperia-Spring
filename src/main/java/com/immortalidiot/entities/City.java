package com.immortalidiot.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    private String name;
    private String area;
    private Set<Cultist> cultists;

    public City(String name, String area) {
        this.name = name;
        this.area = area;
    }

    protected City() {}

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

    @OneToMany(mappedBy = "cities", targetEntity = Cultist.class)
    public Set<Cultist> getCultists() {
        return cultists;
    }

    public void setCultists(Set<Cultist> cultists) {
        this.cultists = cultists;
    }
}
