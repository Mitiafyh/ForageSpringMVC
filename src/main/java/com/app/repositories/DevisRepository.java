package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Devis;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface DevisRepository extends JpaRepository<Devis, Integer> {
    @Query("SELECT DISTINCT d FROM Devis d LEFT JOIN FETCH d.sousDevis LEFT JOIN FETCH d.demande")
    List<Devis> findAllWithSousDevis();
    
    @Query("SELECT d FROM Devis d LEFT JOIN FETCH d.sousDevis LEFT JOIN FETCH d.demande WHERE d.id = :id")
    Devis findByIdWithSousDevis(@Param("id") int id);
}