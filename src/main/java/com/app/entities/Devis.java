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

@Entity
@Table(name = "Devis")
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idDemande")
    private Demande demande;

    @OneToMany(mappedBy = "devis")
    private List<SousDevis> sousDevis;

    private Integer idTypeDevis;

   
    public Devis() {}

    public Devis(Demande demande, Integer type) {
        this.demande = demande;
        this.idTypeDevis=type;
    }

    public List<SousDevis> getSousDevis() {
        return sousDevis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

     public Integer getIdTypeDevis() {
        return idTypeDevis;
    }

    public void setIdTypeDevis(Integer idTypeDevis) {
        this.idTypeDevis = idTypeDevis;
    }


    public void setDemande(Demande demande) {
        this.demande = demande;
    }

 

    public void setSousDevis(List<SousDevis> sousDevis) {
        this.sousDevis = sousDevis;
    }
}