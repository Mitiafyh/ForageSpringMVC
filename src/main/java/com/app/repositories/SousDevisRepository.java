package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.SousDevis;
import java.util.List;

public interface  SousDevisRepository extends JpaRepository<SousDevis, Integer> {
    List<SousDevis> findByDevisId(int devisId);
    void deleteByDevisId(int devisId);
}
