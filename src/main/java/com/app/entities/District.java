package com.app.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "District")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nomDistrict")
    private String nomDistrict;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<Commune> commune;

    @ManyToOne
    @JoinColumn(name = "idRegion") 
    @JsonIgnore
    private Region region;

    public District(){} 
    public District(int id, String nomDistrict, Region region) {
        this.id = id;
        this.nomDistrict = nomDistrict;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomDistrict() {
        return nomDistrict;
    }

    public void setNomDistrict(String nomDistrict) {
        this.nomDistrict = nomDistrict;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }



}
