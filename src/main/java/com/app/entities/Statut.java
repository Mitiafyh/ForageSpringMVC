package com.app.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Statut")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nomStatut")
    private String nomStatut;

    @Column(name = "sigle")
    private String sigle;

    @OneToMany(mappedBy = "statut")
    private List<DemandeStatut> demande;
    
    
    public static final String SIGLE_DEMANDE_CREEE = "DC";
    public static final String SIGLE_DEVIS_ETUDE_CREE = "DEC";
    public static final String SIGLE_DEVIS_ETUDE_REJETE = "DER";
    public static final String SIGLE_DEVIS_ETUDE_ACCEPTE = "DEA";
    public static final String SIGLE_DEVIS_FORAGE_CREE = "DFC";
    public static final String SIGLE_DEVIS_FORAGE_REJETE = "DFR";
    public static final String SIGLE_DEVIS_FORAGE_ACCEPTE = "DFA";
    public static final String SIGLE_FORAGE_COMMENCES = "FC";
    public static final String SIGLE_FORAGE_TERMINES = "FT";


    public Statut(){}
    public Statut(int id, String nomStatut, String sigle) {
        this.id = id;
        this.nomStatut = nomStatut;
        this.sigle = sigle;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomStatut() {
        return nomStatut;
    }
    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }
    public String getSigle() {
        return sigle;
    }
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }
    

}
