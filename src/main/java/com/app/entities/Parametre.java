package com.app.entities;
// create Table Parametre(
//     id INT AUTO_INCREMENT PRIMARY KEY,
//     idStatut1 INT,
//     idStatut2 INT,
//     DT DOUBLE,
//     alerte VARCHAR(20)
// );

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Parametre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "idStatut1")
    private int idStatut1;

    @Column(name = "idStatut2")
    private int idStatut2;

    @Column(name = "DT")
    private Double dt;

    @Column(name = "alerte")
    private String alerte;

    public Parametre() {}

    public Parametre(int idStatut1, int idStatut2, Double dt, String alerte) {
        this.idStatut1 = idStatut1;
        this.idStatut2 = idStatut2;
        this.dt = dt;
        this.alerte = alerte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStatut1() {
        return idStatut1;
    }

    public void setIdStatut1(int idStatut1) {
        this.idStatut1 = idStatut1;
    }

    public int getIdStatut2() {
        return idStatut2;
    }

    public void setIdStatut2(int idStatut2) {
        this.idStatut2 = idStatut2;
    }

    public Double getDt() {
        return dt;
    }

    public void setDt(Double dt) {
        this.dt = dt;
    }

    public String getAlerte() {
        return alerte;
    }

    public void setAlerte(String alerte) {
        this.alerte = alerte;
    }

}
